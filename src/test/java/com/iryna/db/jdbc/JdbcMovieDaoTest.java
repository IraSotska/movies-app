package com.iryna.db.jdbc;

import com.iryna.entity.MovieRequest;
import com.iryna.entity.sort.SortDirection;
import com.iryna.entity.sort.SortField;
import com.iryna.entity.sort.SortType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcMovieDaoTest {

    @Test
    void createQuerySortingTest() {

        String correctQuery = "SELECT id, name_native, year_of_release, rating, price, picture_path FROM movies ORDER BY RATING DESC, PRICE ASC;";
        MovieRequest movieRequest = new MovieRequest(List.of(new SortType(SortField.RATING, SortDirection.DESC),
                new SortType(SortField.PRICE, SortDirection.ASC)));
        JdbcMovieDao jdbcMovieDao = new JdbcMovieDao(null);
        String query = jdbcMovieDao.generateQueryForSorting(movieRequest);
        assertNotNull(query);
        assertEquals(correctQuery, query);
    }
}