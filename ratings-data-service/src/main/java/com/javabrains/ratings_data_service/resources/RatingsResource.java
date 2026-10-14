package com.javabrains.ratings_data_service.resources;

import com.javabrains.ratings_data_service.models.Rating;
import com.javabrains.ratings_data_service.models.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable  String movieId){
        return new Rating(movieId, 3);
    }

    @GetMapping("users/{userId}")
    public UserRatings getUserRatings(@PathVariable String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );
        UserRatings userRatings = new UserRatings();
        userRatings.setUserRatings(ratings);
        return userRatings;
    }
}
