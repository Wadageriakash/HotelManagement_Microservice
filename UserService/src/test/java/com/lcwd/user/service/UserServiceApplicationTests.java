package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.external.service.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("").feedback("this is created using the feign client").build();
		ResponseEntity<Rating> saveRating = ratingService.createRating(rating);
		saveRating.getBody();
		saveRating.getClass();
		saveRating.getHeaders();
		System.out.println("new rating created");
	}

}
