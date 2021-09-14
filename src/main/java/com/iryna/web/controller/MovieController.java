package com.iryna.web.controller;

import com.iryna.entity.Currency;
import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import com.iryna.entity.sort.SortDirection;
import com.iryna.entity.sort.SortField;
import com.iryna.entity.sort.SortType;
import com.iryna.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private MovieServiceImpl movieService;

    @GetMapping
    public Iterable<Movie> findAll(@RequestParam(required = false) String rating,
                                   @RequestParam(required = false) String price) {
        List<SortType> sortTypeList = new ArrayList<>();

        if (rating != null) {
            sortTypeList.add(new SortType(SortField.RATING, SortDirection.valueOf(rating)));
        }
        if (price != null) {
            sortTypeList.add(new SortType(SortField.PRICE, SortDirection.valueOf(price)));
        }
        return movieService.findAll(new MovieRequest(sortTypeList));
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable int id,
                         @RequestParam(required = false) String currency) {
        Currency currentCurrency;
        try {
            currentCurrency = Currency.valueOf(currency.toUpperCase());
        }
        catch (Exception exception) {
            currentCurrency = Currency.UAH;
        }
        return movieService.getById(id, currentCurrency);
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
    public void setMovieService(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }
}
