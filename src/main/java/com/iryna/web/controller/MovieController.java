package com.iryna.web.controller;

import com.iryna.entity.Movie;
import com.iryna.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private MovieService movieService;

    @GetMapping
    public Iterable<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/random")
    public Iterable<Movie> getRandomMovies() {
        return movieService.getRandomMovies();
    }

    @GetMapping("/genre/{genreId}")
    public Iterable<Movie> getMoviesByGenre(@PathVariable String genreId) {
        return movieService.getMoviesByGenre(Integer.parseInt(genreId));
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
