package com.iryna.service.impl;

import com.iryna.db.MovieDao;
import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl {

    private MovieDao movieDao;

    public Iterable<Movie> findAll(MovieRequest movieRequest) {
        return movieDao.findAll(movieRequest);
    }

    public Iterable<Movie> getRandomMovies() {
        return movieDao.getRandomMovies();
    }

    public Iterable<Movie> getMoviesByGenre(int genreId) {
        return movieDao.getMoviesByGenre(genreId);
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
}
