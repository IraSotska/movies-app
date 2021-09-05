package com.iryna.web.controller;

import com.iryna.entity.Genre;
import com.iryna.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private GenreService genreService;

    @GetMapping
    public Iterable<Genre> findAll() {
        return genreService.findAll();
    }

    @Autowired
    public void setMovieService(GenreService genreService) {
        this.genreService = genreService;
    }
}
