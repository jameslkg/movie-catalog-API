package com.javabrains.movie_info_service.resources;

import com.javabrains.movie_info_service.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId){
        return new Movie(movieId, "movieName");
    }
}
