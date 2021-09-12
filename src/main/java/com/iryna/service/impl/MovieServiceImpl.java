package com.iryna.service.impl;

import com.iryna.db.MovieDao;
import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import com.iryna.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao;

    @Override
    public Iterable<Movie> findAll(MovieRequest movieRequest) {
        return movieDao.findAll(movieRequest);
    }

    @Override
    public Iterable<Movie> getRandomMovies() {
        return movieDao.getRandomMovies();
    }

    @Override
    public Iterable<Movie> getMoviesByGenre(int genreId) {
        return movieDao.getMoviesByGenre(genreId);
    }

    @Override
    public Movie getById(int id) {
        return movieDao.getById(id);
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
}
