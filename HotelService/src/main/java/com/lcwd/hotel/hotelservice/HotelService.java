package com.lcwd.hotel.hotelservice;

import java.util.List;

import com.lcwd.hotel.entites.Hotel;

public interface HotelService {

	// create
	
	Hotel create(Hotel hotel);
	
	// get all
	List<Hotel> getAll();
	
	// get single
	Hotel get(Integer id);
	
}
