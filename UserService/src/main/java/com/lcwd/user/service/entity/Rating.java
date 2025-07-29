package com.lcwd.user.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

	private Integer ratingId;
	private String userId;
	private String HotelId;
	private int rating;
	private String feedback;
	private Hotel hotel;
//	List<Hotel> hotel = new ArrayList<>();
	
}
