package com.iryna.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iryna.entity.Movie;

import java.util.List;

public class JsonConverter {

    public static String convertToJson(List<Movie> movie) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
