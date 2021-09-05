package com.iryna.service;

import com.iryna.db.MovieDao;
import com.iryna.entity.Movie;
import com.iryna.entity.Sort;
import com.iryna.utils.comparator.MoviePriceComparator;
import com.iryna.utils.comparator.MovieRatingComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private MovieDao movieDao;

    public Iterable<Movie> getSortedMovies(String rating, String price) {

        Collection<Movie> movies = movieDao.findAll();
        Comparator<Movie> movieComparator = null;

        if (rating != null) {
            rating = rating.toUpperCase();
            if (Sort.valueOf(rating).equals(Sort.ACS)) {
                movieComparator = new MovieRatingComparator().reversed();
            } else if (Sort.valueOf(rating).equals(Sort.DESK)) {
                movieComparator = new MovieRatingComparator();
            }
        }

        if (price != null) {
            price = price.toUpperCase();
            if (Sort.valueOf(price).equals(Sort.ACS)) {
                if (movieComparator != null) {
                    movieComparator.thenComparing(new MoviePriceComparator().reversed());
                } else {
                    movieComparator = new MoviePriceComparator().reversed();
                }
            } else if (Sort.valueOf(price).equals(Sort.DESK)) {
                if (movieComparator != null) {
                    movieComparator.thenComparing(new MoviePriceComparator());
                } else {
                    movieComparator = new MoviePriceComparator();
                }
            }
        }

        if (movieComparator != null) {
            return movies
                    .stream()
                    .sorted(movieComparator)
                    .collect(Collectors.toList());
        } else {
            return movies;
        }
    }

    public Iterable<Movie> getRandomMovies() {
        return movieDao.getRandomMovies();
    }

    public Iterable<Movie> getMoviesByGenre(int genreId) {
        return movieDao.getMoviesByGenre(genreId);
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
}
