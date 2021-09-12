package com.iryna.db;

import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll(MovieRequest movieRequest);

    List<Movie> getRandomMovies();

    List<Movie> getMoviesByGenre(int genreId);

    Movie getById(int id);
}
