package com.iryna.service;

import com.iryna.db.MovieDao;
import com.iryna.entity.Movie;
import lombok.Setter;

@Setter
public class MovieService {

    private MovieDao movieDao;

    public Iterable<Movie> findAll() {
        return movieDao.findAll();
    }
}
