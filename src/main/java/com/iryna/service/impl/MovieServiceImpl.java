package com.iryna.service.impl;

import com.iryna.db.MovieDao;
import com.iryna.entity.Currency;
import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import com.iryna.service.MovieService;
import com.iryna.util.CurrencyScrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao;
    private CurrencyScrapperImpl currencyScrapper;

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
    public Movie getById(int id, Currency currency) {

        Movie movie = movieDao.getById(id);
        if(!currency.equals(Currency.UAH)) {
            movie.setPrice(movie.getPrice()/currencyScrapper.getActualPrice(currency));
        }
        return movie;
    }

    @Autowired
    public void setCurrencyScrapper(CurrencyScrapperImpl currencyScrapper) {
        this.currencyScrapper = currencyScrapper;
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
}
