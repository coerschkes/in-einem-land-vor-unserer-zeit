package com.github.coerschkes.application.api;

import com.github.coerschkes.application.external.hardware.daemon.EmuConnector;
import net.sf.yad2xx.FTDIException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/emu")
public class EmuService {

    @GET
    @Path("/power")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCurrentPower() {
        try {
            return Response.ok(EmuConnector.getInstance().getCurrentPower().join()).build();
        } catch (RuntimeException e) {
            return Response.serverError().entity("{ \"error\": \"Unable to connect to device\" }").build();
        }
    }
}