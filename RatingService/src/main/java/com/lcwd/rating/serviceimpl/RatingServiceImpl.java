package com.lcwd.rating.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingrepository;
	
	@Override
	public Rating create(Rating rating) {
		return ratingrepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingrepository.findAll();
	}

	@Override
	public List<Rating> getAllRatingByUserId(String userId) {
		return ratingrepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(Integer hotelId) {
		return ratingrepository.findByHotelId(hotelId);
	}

}
