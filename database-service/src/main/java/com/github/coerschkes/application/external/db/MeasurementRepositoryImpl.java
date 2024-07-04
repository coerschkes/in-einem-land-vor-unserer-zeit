package com.github.coerschkes.application.external.db;

import com.github.coerschkes.domain.model.Measurement;
import com.github.coerschkes.domain.model.MeasurementSeries;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

public class MeasurementRepositoryImpl implements MeasurementRepository {
    private final MysqlConnector mysqlConnector;

    public MeasurementRepositoryImpl() {
        mysqlConnector = new MysqlConnector();
    }

    @Override
    public Measurement[] readMeasurementsFromSeries(final int measurementSeriesId) throws SQLException, ClassNotFoundException {
        return this.mysqlConnector.executeQuery(QueryBuilder.selectMeasurementWithSeriesId(measurementSeriesId), ResultTransformer::toMeasurements);
    }

    @Override
    public MeasurementSeries[] readAllMeasurementSeries() throws SQLException, ClassNotFoundException {
        final MeasurementSeries[] allMeasurementSeries = this.mysqlConnector.executeQuery(QueryBuilder.selectAllMeasurementSeries(), ResultTransformer::toMeasurementSeries);
        for (MeasurementSeries measurementSeries : allMeasurementSeries) {
            measurementSeries.setMeasurements(readMeasurementsFromSeries(measurementSeries.getMeasurementSeriesId()));
        }
        return allMeasurementSeries;
    }

    @Override
    public void saveMeasurement(final int measurementSeriesId, final Measurement measurement) throws SQLException, ClassNotFoundException {
        MeasurementSeries measurementSeries = Arrays.stream(this.readAllMeasurementSeries()).filter(ms -> ms.getMeasurementSeriesId() == measurementSeriesId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MeasurementSeries not found"));
        Arrays.stream(measurementSeries.getMeasurements())
                .max(Comparator.comparing(Measurement::getMeasurementId))
                .ifPresent(m -> {
                    if (measurement.getTimeMillis() - m.getTimeMillis() < measurementSeries.getTimeMillis()) {
                        throw new IllegalArgumentException("Measurement too fast");
                    }
                });
        this.mysqlConnector.executeUpdate(QueryBuilder.insertIntoMeasurement(measurement, measurementSeriesId));
    }

    @Override
    public void saveMeasurementSeries(final MeasurementSeries measurementSeries) throws SQLException, ClassNotFoundException {
        this.mysqlConnector.executeUpdate(QueryBuilder.insertIntoMeasurementSeries(measurementSeries));
    }

    @Override
    public void deleteMeasurementsFromSeries(int measurementSeriesId) throws SQLException, ClassNotFoundException {
        this.mysqlConnector.executeUpdate(QueryBuilder.deleteMeasurementsFromSeries(measurementSeriesId));
    }
}
