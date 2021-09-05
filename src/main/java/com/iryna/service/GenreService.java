package com.iryna.service;

import com.iryna.db.GenreDao;
import com.iryna.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private GenreDao genreDao;

    public Iterable<Genre> findAll() {
        return genreDao.findAll();
    }

    @Autowired
    public void setMovieDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
}
