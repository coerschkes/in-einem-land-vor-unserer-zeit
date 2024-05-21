package com.github.coerschkes.application.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericObjectMapper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(final T t) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(t);
    }
}
