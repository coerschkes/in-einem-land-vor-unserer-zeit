package com.github.coerschkes.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A value object used for communication
 */
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
        this.measurementId = measurementId;
        this.measurementSeriesId = measurementSeriesId;
        this.measurementValue = measurementValue;
        this.timeMillis = timeMillis;
    }
}
