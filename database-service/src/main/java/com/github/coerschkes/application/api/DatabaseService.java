package com.github.coerschkes.application.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coerschkes.application.external.db.MeasurementRepository;
import com.github.coerschkes.application.external.db.MeasurementRepositoryImpl;
import com.github.coerschkes.domain.model.Measurement;
import com.github.coerschkes.domain.model.MeasurementSeries;
import com.github.coerschkes.util.GenericObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;

@Path("/measurementSeries")
public class DatabaseService {
    private final MeasurementRepository repository;

    public DatabaseService() {
        this.repository = new MeasurementRepositoryImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response measurementSeries() throws SQLException, ClassNotFoundException, JsonProcessingException {
        final String measurementSeriesJson = GenericObjectMapper.toJson(repository.readAllMeasurementSeries());
        return Response.ok(measurementSeriesJson).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response measurements(final @PathParam("id") int id) throws SQLException, ClassNotFoundException, JsonProcessingException {
        final String measurementsJson = GenericObjectMapper.toJson(repository.readMeasurementsFromSeries(id));
        return Response.ok(measurementsJson).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMeasurementSeries(final String measurementSeriesJson) throws SQLException, ClassNotFoundException, JsonProcessingException {
        final MeasurementSeries measurementSeries = GenericObjectMapper.fromJson(measurementSeriesJson, MeasurementSeries.class);
        repository.saveMeasurementSeries(measurementSeries);
        return Response.created(URI.create("/measurement/" + measurementSeries.getMeasurementSeriesId())).build();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMeasurementForSeries(final @PathParam("id") int id, final String measurementJson) throws JsonProcessingException, SQLException, ClassNotFoundException {
        final Measurement measurement = GenericObjectMapper.fromJson(measurementJson, Measurement.class);
        repository.saveMeasurement(id, measurement);
        return Response.created(URI.create("/measurement/" + id)).build();
    }
}