package com.iryna.db.jdbc;

import com.iryna.db.MovieDao;
import com.iryna.db.mapper.MovieRowMapper;
import com.iryna.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@AllArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private static final String GET_ALL_MOVIES_QUERY = "SELECT * FROM movies;";

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private JdbcTemplate jdbcTemplate;

    public List<Movie> findAll() {
        return jdbcTemplate.query(GET_ALL_MOVIES_QUERY, MOVIE_ROW_MAPPER);
    }
}
