package com.lcwd.user.service.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Rating {

	private Integer ratingId;
	private String userId;
	private String HotelId;
	private int rating;
	private String feedback;
	
	
}
