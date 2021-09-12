package com.iryna.service;

import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;

public interface MovieService {

    Iterable<Movie> findAll(MovieRequest movieRequest);

    Iterable<Movie> getRandomMovies();

    Iterable<Movie> getMoviesByGenre(int genreId);

    Movie getById(int id);
}
