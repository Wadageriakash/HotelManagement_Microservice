package com.lcwd.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entity.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

	// No need to write the implementation for the calling method that is the advantage of the Feign Client.
	
	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
	
}
