package com.iryna.service.impl;

import com.iryna.config.Configuration;
import com.iryna.db.GenreDao;
import com.iryna.entity.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {Configuration.class})
class GenreServiceImplTest {

//    @Autowired
//    GenreServiceImpl genreServiceImpl;
//
//    @Test
//    void getCashedGenresTest() {
//
//        List<Genre> oldGenres = List.of(Genre.builder().name("old name").build());
//        List<Genre> newGenres = List.of(Genre.builder().name("new name").build());
//
//        GenreDao genreDao = mock(GenreDao.class);
//        when(genreDao.findAll()).thenReturn(oldGenres).thenReturn(newGenres);
//
//        genreServiceImpl.setMovieDao(genreDao);
//
//        assertEquals(oldGenres, genreServiceImpl.findAll());
//        assertEquals(oldGenres, genreServiceImpl.findAll());
//
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        assertEquals(newGenres, genreServiceImpl.findAll());
//    }
}