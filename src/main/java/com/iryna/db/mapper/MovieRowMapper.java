package com.iryna.db.mapper;

import com.iryna.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {

        long id = resultSet.getLong("id");
        String name = resultSet.getString("name_native");
        Date yearOfRelease = resultSet.getDate("year_of_release");
        double rating = resultSet.getDouble("rating");
        int price = resultSet.getInt("price");
        String picturePath = resultSet.getString("picture_path");

        return Movie.builder()
                .id(id)
                .name(name)
                .rating(rating)
                .yearOfRelease(yearOfRelease)
                .picturePath(picturePath)
                .price(price)
                .build();
    }
}