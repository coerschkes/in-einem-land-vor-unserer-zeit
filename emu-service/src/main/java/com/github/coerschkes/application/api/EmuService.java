package com.github.coerschkes.application.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.coerschkes.application.api.util.GenericObjectMapper;
import com.github.coerschkes.application.external.hardware.daemon.EmuConnector;
import com.github.coerschkes.domain.model.Measurement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

@Path("/emu")
public class EmuService {
    @GET
    @Path("/power")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCurrentPower() throws JsonProcessingException, ExecutionException, InterruptedException {
        return Response.ok(GenericObjectMapper.toJson(createMeasurement(EmuConnector.getInstance().getCurrentPower().get()))).build();
    }

    private Measurement createMeasurement(final String measurementValue) {
        return new Measurement(0, 0, Double.parseDouble(measurementValue), System.currentTimeMillis());
    }
}