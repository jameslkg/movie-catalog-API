package com.javabrains.movie_catalog_service.resources;

import com.javabrains.movie_catalog_service.models.CatalogItem;
import com.javabrains.movie_catalog_service.models.Movie;
import com.javabrains.movie_catalog_service.models.Rating;
import com.javabrains.movie_catalog_service.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestClient restClient;

    public MovieCatalogResource(RestClient restClient) {
        this.restClient = restClient;
    }


    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        UserRatings userRatings = restClient.get().uri("http://ratings-data-service/ratingsdata/users/"+userId).retrieve().body(UserRatings.class);

        return userRatings.getUserRatings().stream().map(rating -> {
            Movie movie = restClient.get().uri("http://movie-info-service/movies/" + rating.getMovieId()).retrieve().body(Movie.class);

            return new CatalogItem(movie.getName(), "desc", rating.getRating());
        }).collect(Collectors.toList());
    }
}
