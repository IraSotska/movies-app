package com.iryna.db.mapper;

import com.iryna.entity.Genre;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GenreRowMapperTest {

    @Test
    void mapRow() {

        ResultSet resultSet = mock(ResultSet.class);
        try {
            when(resultSet.getLong("id")).thenReturn(1L);
            when(resultSet.getString("name")).thenReturn("Name");

            GenreRowMapper genreRowMapper = new GenreRowMapper();
            Genre genre = genreRowMapper.mapRow(resultSet, 0);

            assertNotNull(genre);
            assertEquals("Name", genre.getName());
            assertEquals(1L, genre.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}