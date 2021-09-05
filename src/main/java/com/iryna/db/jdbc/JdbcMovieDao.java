package com.iryna.db.jdbc;

import com.iryna.db.MovieDao;
import com.iryna.db.mapper.MovieRowMapper;
import com.iryna.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private static final int COUNT_RANDOM_MOVIES = 3;
    private static final String GET_ALL_MOVIES_QUERY = "SELECT id, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies;";
    private static final String GET_RANDOM_MOVIES_QUERY = "SELECT id, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies ORDER BY random() LIMIT " + COUNT_RANDOM_MOVIES + ";";
    private static final String GET_MOVIES_BY_GENRE_ID = "select id, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies LEFT JOIN movie_genre ON movie_genre.movie_id = movies.id " +
            "where movie_genre.genre_id = ?;";

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private JdbcTemplate jdbcTemplate;

    public List<Movie> findAll() {
        return jdbcTemplate.query(GET_ALL_MOVIES_QUERY, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandomMovies() {
        return jdbcTemplate.query(GET_RANDOM_MOVIES_QUERY, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genreId) {
        return jdbcTemplate.query
                (GET_MOVIES_BY_GENRE_ID,
                        new Object[]{genreId},
                        MOVIE_ROW_MAPPER);
    }
}
