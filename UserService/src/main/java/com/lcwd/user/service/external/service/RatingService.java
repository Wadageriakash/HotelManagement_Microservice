package com.lcwd.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entity.Rating;


@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//  get
	
	// Post
	@PostMapping("/rating")
	public ResponseEntity<Rating> createRating(Rating values);
	
	// put
	@PutMapping("/rating/{ratingId}")
	public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);
	
	// For Deleting
	@DeleteMapping("/rating/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
