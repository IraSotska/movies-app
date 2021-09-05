package com.iryna.service;

import com.iryna.db.MovieDao;
import com.iryna.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private MovieDao movieDao;

    public Iterable<Movie> findAll() {
        return movieDao.findAll();
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
