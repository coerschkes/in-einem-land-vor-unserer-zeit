package com.github.coerschkes.application.external.db;


import com.github.coerschkes.domain.model.Measurement;
import com.github.coerschkes.domain.model.MeasurementSeries;

import java.sql.SQLException;

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
        System.out.println("Executing update on db: " + QueryBuilder.insertIntoMeasurement(measurement, measurementSeriesId));
        this.mysqlConnector.executeUpdate(QueryBuilder.insertIntoMeasurement(measurement, measurementSeriesId));
    }

    @Override
    public void saveMeasurementSeries(MeasurementSeries measurementSeries) throws SQLException, ClassNotFoundException {
        this.mysqlConnector.executeUpdate(QueryBuilder.insertIntoMeasurementSeries(measurementSeries));
    }
}
