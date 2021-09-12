package com.iryna.web.controller;

import com.iryna.entity.Movie;
import com.iryna.entity.MovieRequest;
import com.iryna.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GenreControllerTest {

    private MockMvc mockMvc;
    private MovieServiceImpl movieService;

    @BeforeEach
    public void setup() {

        movieService = mock(MovieServiceImpl.class);

        MovieController movieController = new MovieController();
        movieController.setMovieService(movieService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

    }

    @Test
    void findAll() throws Exception {
        Movie movie1 = Movie.builder()
                .id(1)
                .name("name1")
                .price(12.90)
                .rating(54.09)
                .build();
        Movie movie2 = Movie.builder()
                .id(2)
                .name("name2")
                .price(1.0)
                .rating(3.9)
                .build();


        Iterable<Movie> movies = List.of(movie1, movie2);
        when(movieService.findAll(new MovieRequest(null))).thenReturn(movies);

        mockMvc.perform(get("/movie")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("movie1"))
                .andExpect(jsonPath("$[0].price").value(12.90D))
                .andExpect(jsonPath("$[0].rating").value(54.09D))

                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("name2"))
                .andExpect(jsonPath("$[1].price").value(1.0D))
                .andExpect(jsonPath("$[1].rating").value(3.9D));

        verify(movieService, times(1)).findAll(new MovieRequest(null));
        verifyNoMoreInteractions(movieService);
    }
}