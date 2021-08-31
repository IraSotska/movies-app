package com.iryna.web.controller;

import com.iryna.converter.JsonConverter;
import com.iryna.entity.Movie;
import com.iryna.service.MovieService;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Setter
@Controller
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @ResponseBody
    @GetMapping
    public String findAll(HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");
        return JsonConverter.convertToJson((List<Movie>) movieService.findAll());
    }
}
