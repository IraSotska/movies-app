package com.iryna.converter;

import com.iryna.entity.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {

    @Test
    void convertToJson() {

        String json = "[{\"id\":1,\"name\":\"The Shawshank Redemption\",\"yearOfRelease\":1994,\"rating\":8.89,\"picturePath\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg\",\"price\":134.67},{\"id\":2,\"name\":\"The Green Mile\",\"yearOfRelease\":1999,\"rating\":8.88,\"picturePath\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg\",\"price\":134.67}]";
        Movie movie1 = Movie.builder()
                .id(1)
                .name("The Shawshank Redemption")
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .rating(8.89)
                .yearOfRelease(1994)
                .price(134.67)
                .build();

        Movie movie2 = Movie.builder()
                .id(2)
                .name("The Green Mile")
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .rating(8.88)
                .yearOfRelease(1999)
                .price(134.67)
                .build();

        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        assertEquals(json, JsonConverter.convertToJson(movies));
    }
}