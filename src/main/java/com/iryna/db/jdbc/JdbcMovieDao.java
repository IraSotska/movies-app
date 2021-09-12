package com.iryna.db.jdbc;

import com.iryna.db.MovieDao;
import com.iryna.db.mapper.MovieRowMapper;
import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import com.iryna.entity.sort.SortType;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Repository
@AllArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private static final int COUNT_RANDOM_MOVIES = 3;
    private static final String GET_ALL_MOVIES_QUERY = "SELECT id, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies";
    private static final String GET_RANDOM_MOVIES_QUERY = "SELECT id, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies ORDER BY random() LIMIT " + COUNT_RANDOM_MOVIES + ";";
    private static final String GET_MOVIES_BY_GENRE_ID = "select id, name_native, year_of_release, rating, price, " +
            "picture_path FROM movies LEFT JOIN movie_genre ON movie_genre.movie_id = movies.id " +
            "where movie_genre.genre_id = ?;";

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private JdbcTemplate jdbcTemplate;

    public List<Movie> findAll(MovieRequest movieRequest) {
        return jdbcTemplate.query(generateQueryForSorting(movieRequest), MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getRandomMovies() {
        return jdbcTemplate.query(GET_RANDOM_MOVIES_QUERY, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getMoviesByGenre(int genreId) {
        return jdbcTemplate.query(GET_MOVIES_BY_GENRE_ID, MOVIE_ROW_MAPPER, genreId);
    }

    protected String generateQueryForSorting(MovieRequest movieRequest) {

        if (movieRequest == null) {
            return GET_ALL_MOVIES_QUERY + ";";
        } else {
            StringJoiner joiner = new StringJoiner(", ", GET_ALL_MOVIES_QUERY + " ORDER BY ", ";");

            for (SortType sortType : movieRequest.getSortTypeList()) {
                joiner.add(sortType.getSortField() + " " + sortType.getSortDirection());
            }
            return joiner.toString();
        }
    }
}
