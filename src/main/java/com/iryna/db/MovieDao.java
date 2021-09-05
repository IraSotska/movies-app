package com.iryna.db;

import com.iryna.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
    List<Movie> getRandomMovies();
    List<Movie> getMoviesByGenre(int genreId);
}
