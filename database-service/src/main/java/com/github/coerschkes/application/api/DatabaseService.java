package com.github.coerschkes.application.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coerschkes.application.external.db.MeasurementRepository;
import com.github.coerschkes.application.external.db.MeasurementRepositoryImpl;
import com.github.coerschkes.util.GenericObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/test")
public class DatabaseService {
    private final MeasurementRepository repository;

    public DatabaseService() {
        this.repository = new MeasurementRepositoryImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test() throws SQLException, ClassNotFoundException, JsonProcessingException {
        return GenericObjectMapper.toJson(repository.readAllMeasurementSeries());
    }
}