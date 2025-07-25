package com.lcwd.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lcwd.rating.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	// custom finder methods
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(Integer hotelId);
}