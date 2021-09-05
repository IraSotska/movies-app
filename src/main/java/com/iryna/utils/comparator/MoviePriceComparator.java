package com.iryna.utils.comparator;

import com.iryna.entity.Movie;

import java.util.Comparator;

public class MoviePriceComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie m1, Movie m2) {
        return Double.compare(m1.getPrice(), m2.getPrice());
    }
}