package com.iryna.service.impl;

import com.iryna.db.GenreDao;
import com.iryna.entity.Genre;
import com.iryna.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;

    @Cacheable("genres")
    public Iterable<Genre> findAll() {
        log.info("Find all genres");
        return genreDao.findAll();
    }

    @Autowired
    public void setMovieDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
}
