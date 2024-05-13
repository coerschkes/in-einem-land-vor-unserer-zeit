package com.github.coerschkes.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Measurement {

    private final int measurementId;
    private final double measurementValue;
    private final long timeMillis;

    public Measurement(final int measurementId,
                       final double measurementValue,
                       final long timeMillis) {
        super();
        this.measurementId = measurementId;
        this.measurementValue = measurementValue;
        this.timeMillis = timeMillis;
    }

    public Measurement(final @JsonProperty("measurementValue") double measurementValue,
                       final @JsonProperty("timeMillis") long timeMillis) {
        super();
        this.measurementId = 0;
        this.measurementValue = measurementValue;
        this.timeMillis = timeMillis;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }


    public long getTimeMillis() {
        return timeMillis;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementId=" + measurementId +
                ", measurementValue=" + measurementValue +
                ", timeMillis=" + timeMillis +
                '}';
    }
}
