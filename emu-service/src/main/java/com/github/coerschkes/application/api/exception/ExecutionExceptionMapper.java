package com.github.coerschkes.application.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.ExecutionException;

@Provider
public class ExecutionExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<ExecutionException> {

    @Override
    public Response toResponse(ExecutionException e) {
        LOGGER.error("Unable to execute: ", e);
        return Response
                .serverError()
                .entity("{ \"error\": \"Unable to execute request\" }")
                .build();
    }
}
