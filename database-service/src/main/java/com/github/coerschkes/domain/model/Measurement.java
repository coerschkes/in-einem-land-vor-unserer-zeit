package com.github.coerschkes.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coerschkes.util.GenericObjectMapper;

public class Measurement {

    private final int measurementId;
    private final double measurementValue;
    private final long timeMillis;

    public Measurement(final int measurementId, final double measurementValue, final long timeMillis) {
        super();
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


    public String getAttributes() {
        return this.measurementId + ": " + this.measurementValue;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public String toJson() throws JsonProcessingException {
        return GenericObjectMapper.toJson(this);
    }

    public static Measurement fromJson(final String json) throws JsonProcessingException {
        return GenericObjectMapper.fromJson(json, Measurement.class);
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
