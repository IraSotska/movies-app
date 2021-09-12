package com.iryna.web.controller;

import com.iryna.entity.Genre;
import com.iryna.service.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/genre")
public class GenreController {

    private GenreServiceImpl genreService;

    @GetMapping
    public Iterable<Genre> findAll() {
        return genreService.findAll();
    }

    @Autowired
    public void setMovieService(GenreServiceImpl genreService) {
        this.genreService = genreService;
    }
}
