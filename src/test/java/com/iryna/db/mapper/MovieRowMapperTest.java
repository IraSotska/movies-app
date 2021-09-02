package com.iryna.db.mapper;

import com.iryna.entity.Movie;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieRowMapperTest {

    @Test
    void mapRow() {

        ResultSet resultSet = mock(ResultSet.class);
        try {
            when(resultSet.getLong("id")).thenReturn(1L);
            when(resultSet.getString("name_native")).thenReturn("Name");
            when(resultSet.getInt("year_of_release")).thenReturn(1997);
            when(resultSet.getDouble("rating")).thenReturn(0.1D);
            when(resultSet.getInt("price")).thenReturn(999);
            when(resultSet.getString("picture_path")).thenReturn("picture.com");

            MovieRowMapper movieRowMapper = new MovieRowMapper();
            Movie movie = (Movie) movieRowMapper.mapRow(resultSet, 0);

            assertNotNull(movie);
            assertEquals("Name", movie.getName());
            assertEquals(1997, movie.getYearOfRelease());
            assertEquals(0.1D, movie.getRating());
            assertEquals(999, movie.getPrice());
            assertEquals("picture.com", movie.getPicturePath());
            assertEquals(1L, movie.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}