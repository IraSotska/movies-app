package com.iryna.service;

import com.iryna.db.MovieDao;
import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import com.iryna.entity.sort.SortDirection;
import com.iryna.entity.sort.SortField;
import com.iryna.entity.sort.SortType;
import com.iryna.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    @DisplayName("sorting movies by price DESK test")
    @Test
    void sortingByPriceDeskTest() {

        Movie movie1 = Movie
                .builder()
                .name("mov1")
                .price(190)
                .build();

        Movie movie2 = Movie
                .builder()
                .name("mov2")
                .price(100)
                .build();

        Movie movie3 = Movie
                .builder()
                .name("mov3")
                .price(50)
                .build();

        List<Movie> movies = List.of(movie1, movie2, movie3);

        MovieRequest movieRequest = new MovieRequest(List.of(new SortType(SortField.RATING, SortDirection.DESC)));
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll(movieRequest)).thenReturn(movies);

        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.setMovieDao(movieDao);
        List<Movie> result = (List<Movie>) movieService.findAll(movieRequest);

        assertNotNull(result);
        assertEquals(movie1, result.get(0));
        assertEquals(movie2, result.get(1));
        assertEquals(movie3, result.get(2));
    }

    @DisplayName("sorting movies by price ACS test")
    @Test
    void sortingByPriceAcsTest() {

        Movie movie2 = Movie
                .builder()
                .name("mov2")
                .price(100)
                .build();

        Movie movie1 = Movie
                .builder()
                .name("mov1")
                .price(190)
                .build();

        Movie movie3 = Movie
                .builder()
                .name("mov3")
                .price(50)
                .build();

        List<Movie> movies = List.of(movie1, movie2, movie3);

        MovieRequest movieRequest = new MovieRequest(List.of(new SortType(SortField.PRICE, SortDirection.ASC)));
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll(movieRequest)).thenReturn(movies);

        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.setMovieDao(movieDao);

        List<Movie> result = (List<Movie>) movieService.findAll(movieRequest);

        assertNotNull(result);
        assertEquals(movie1, result.get(0));
        assertEquals(movie2, result.get(1));
        assertEquals(movie3, result.get(2));
    }

    @DisplayName("sorting movies by rating ACS test")
    @Test
    void sortingByRatingAcsTest() {

        Movie movie2 = Movie
                .builder()
                .name("mov2")
                .rating(100)
                .build();

        Movie movie1 = Movie
                .builder()
                .name("mov1")
                .rating(190)
                .build();

        Movie movie3 = Movie
                .builder()
                .name("mov3")
                .rating(50)
                .build();

        MovieRequest movieRequest = new MovieRequest(List.of(new SortType(SortField.RATING, SortDirection.ASC)));
        List<Movie> movies = List.of(movie1, movie2, movie3);

        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll(movieRequest)).thenReturn(movies);

        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.setMovieDao(movieDao);
        List<Movie> result = (List<Movie>) movieService.findAll(movieRequest);

        assertNotNull(result);
        assertEquals(movie1, result.get(0));
        assertEquals(movie2, result.get(1));
        assertEquals(movie3, result.get(2));
    }

    @DisplayName("sorting movies by price DESK test")
    @Test
    void sortingByRatingDeskTest() {

        Movie movie1 = Movie
                .builder()
                .name("mov1")
                .rating(190)
                .build();

        Movie movie2 = Movie
                .builder()
                .name("mov2")
                .rating(100)
                .build();

        Movie movie3 = Movie
                .builder()
                .name("mov3")
                .rating(50)
                .build();

        List<Movie> movies = List.of(movie1, movie2, movie3);

        MovieRequest movieRequest = new MovieRequest(List.of(new SortType(SortField.RATING, SortDirection.DESC)));
        MovieDao movieDao = mock(MovieDao.class);
        when(movieDao.findAll(movieRequest)).thenReturn(movies);

        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.setMovieDao(movieDao);
        List<Movie> result = (List<Movie>) movieService.findAll(movieRequest);

        assertNotNull(result);
        assertEquals(movie1, result.get(0));
        assertEquals(movie2, result.get(1));
        assertEquals(movie3, result.get(2));
    }

    @DisplayName("sorting movies by price and rating DESK test")
    @Test
    void sortingByRatingAndPriceDeskTest() {

        Movie movie1 = Movie
                .builder()
                .name("mov1")
                .price(60)
                .rating(190)
                .build();

        Movie movie2 = Movie
                .builder()
                .name("mov2")
                .price(45)
                .rating(190)
                .build();

        Movie movie3 = Movie
                .builder()
                .price(180)
                .name("mov3")
                .rating(50)
                .build();

        List<Movie> movies = List.of(movie1, movie2, movie3);

        MovieDao movieDao = mock(MovieDao.class);
        MovieRequest movieRequest = new MovieRequest(List.of(
                new SortType(SortField.PRICE, SortDirection.ASC),
                new SortType(SortField.RATING, SortDirection.ASC)));
        when(movieDao.findAll(movieRequest)).thenReturn(movies);

        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.setMovieDao(movieDao);
        List<Movie> result = (List<Movie>) movieService.findAll(movieRequest);

        assertNotNull(result);
        assertEquals(movie1, result.get(0));
        assertEquals(movie2, result.get(1));
        assertEquals(movie3, result.get(2));
    }
}