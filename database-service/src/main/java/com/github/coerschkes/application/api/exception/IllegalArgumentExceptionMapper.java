package com.github.coerschkes.application.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(final IllegalArgumentException e) {
        return Response
                .status(400)
                .entity("Illegal request arguments: " + e.getMessage())
                .build();
    }
}
