package com.github.coerschkes.application.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClassNotFoundExceptionMapper implements ExceptionMapper<ClassNotFoundException> {
    @Override
    public Response toResponse(final ClassNotFoundException e) {
        return Response
                .serverError()
                .entity("Error loading class: " + e.getMessage())
                .build();
    }
}
