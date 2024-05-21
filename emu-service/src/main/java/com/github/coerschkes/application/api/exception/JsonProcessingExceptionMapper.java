package com.github.coerschkes.application.api.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(final JsonProcessingException e) {
        LOGGER.error("Unable to process json", e);
        return Response
                .status(400)
                .entity("Unable to process json: " + e.getMessage())
                .build();
    }
}
