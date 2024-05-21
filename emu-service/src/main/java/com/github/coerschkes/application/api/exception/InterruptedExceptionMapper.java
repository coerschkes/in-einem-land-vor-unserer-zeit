package com.github.coerschkes.application.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InterruptedExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<InterruptedException> {
    @Override
    public Response toResponse(InterruptedException e) {
        LOGGER.error("Daemon thread interrupted: ", e);
        return Response
                .serverError()
                .entity("{ \"error\": \"Unable to communicate to daemon thread\" }")
                .build();
    }
}
