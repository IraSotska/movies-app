package com.iryna.db.mapper;

import com.iryna.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper {
    public Movie mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name_native");
        int yearOfRelease = resultSet.getInt("year_of_release");
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

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return mapRow(resultSet);
    }
}