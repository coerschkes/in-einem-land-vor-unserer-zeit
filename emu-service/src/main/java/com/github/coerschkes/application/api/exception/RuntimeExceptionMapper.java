package com.github.coerschkes.application.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        LOGGER.error("Unable to connect to emu device: ", e);
        return Response
                .serverError()
                .entity("{ \"error\": \"Unable to connect to device\" }")
                .build();
    }
}

