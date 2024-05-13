package com.github.coerschkes.application.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(final JsonProcessingException e) {
        return Response
                .status(400)
                .entity("Unable to process json: " + e.getMessage())
                .build();
    }
}
