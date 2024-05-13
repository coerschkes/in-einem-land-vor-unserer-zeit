package com.github.coerschkes.application.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

@Provider
public class SqlExceptionMapper implements ExceptionMapper<SQLException> {
    @Override
    public Response toResponse(final SQLException e) {
        return Response
                .status(400)
                .entity("Error when executing query: " + e.getMessage())
                .build();
    }
}