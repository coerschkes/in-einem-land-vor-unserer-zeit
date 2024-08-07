package com.github.coerschkes.application.external.db;

import com.github.coerschkes.domain.model.Measurement;
import com.github.coerschkes.domain.model.MeasurementSeries;

class QueryBuilder {
    private static final String QUERY_SELECT_MEASUREMENT_WITH_SERIES_ID = "SELECT * FROM measurement WHERE measurementSeriesId = %s";
    private static final String QUERY_INSERT_INTO_MEASUREMENT = "INSERT INTO measurement (measurementValue, timeMillis, measurementSeriesId) VALUES(%s, %s, %s)";
    private static final String QUERY_SELECT_ALL_MEASUREMENT_SERIES = "SELECT * FROM measurementSeries";
    private static final String QUERY_INSERT_INTO_MEASUREMENT_SERIES = "INSERT INTO measurementSeries (measurementSeriesId, timeInterval, consumer, measurementSize) VALUES(%s, %s, '%s', '%s')";
    private static final String QUERY_DELETE_MEASUREMENTS_FROM_SERIES = "DELETE FROM measurement WHERE measurementSeriesId = %s";
    private static final String QUERY_DELETE_MEASUREMENT_SERIES = "DELETE FROM measurementSeries WHERE measurementSeriesId = %s";

    static String selectMeasurementWithSeriesId(final int measurementSeriesId) {
        return String.format(QUERY_SELECT_MEASUREMENT_WITH_SERIES_ID, measurementSeriesId);
    }

    static String insertIntoMeasurement(final Measurement measurement, final int measurementSeriesId) {
        return String.format(QUERY_INSERT_INTO_MEASUREMENT, measurement.getMeasurementValue(), measurement.getTimeMillis(), measurementSeriesId);
    }

    static String selectAllMeasurementSeries() {
        return QUERY_SELECT_ALL_MEASUREMENT_SERIES;
    }

    static String insertIntoMeasurementSeries(final MeasurementSeries measurementSeries) {
        return String.format(QUERY_INSERT_INTO_MEASUREMENT_SERIES, measurementSeries.getMeasurementSeriesId(), measurementSeries.getTimeMillis(), measurementSeries.getConsumer(), measurementSeries.getMeasurementSize());
    }

    static String deleteMeasurementsFromSeries(final int measurementSeriesId) {
        return String.format(QUERY_DELETE_MEASUREMENTS_FROM_SERIES, measurementSeriesId);
    }

    public static String deleteMeasurementSeries(int measurementSeriesId) {
        return String.format(QUERY_DELETE_MEASUREMENT_SERIES, measurementSeriesId);
    }
}
