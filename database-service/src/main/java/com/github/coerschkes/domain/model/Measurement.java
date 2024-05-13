package com.github.coerschkes.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Measurement {

    private final int measurementId;
    private final int measurementSeriesId;
    private final double measurementValue;
    private final long timeMillis;


    public Measurement(final @JsonProperty("measurementId") int measurementId,
                       final @JsonProperty("measurementSeriesId") int measurementSeriesId,
                       final @JsonProperty("measurementValue") double measurementValue,
                       final @JsonProperty("timeMillis") long timeMillis) {
        super();
        this.measurementSeriesId = measurementSeriesId;
        this.measurementId = measurementId;
        this.measurementValue = measurementValue;
        this.timeMillis = timeMillis;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }


    public long getTimeMillis() {
        return timeMillis;
    }

    public int getMeasurementSeriesId() {
        return measurementSeriesId;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementId=" + measurementId +
                ", measurementSeriesId=" + measurementSeriesId +
                ", measurementValue=" + measurementValue +
                ", timeMillis=" + timeMillis +
                '}';
    }
}
