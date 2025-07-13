package com.lcwd.hotel.hotelserviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.hotelservice.HotelService;
import com.lcwd.hotel.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelrepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		return hotelrepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelrepository.findAll();
	}

	@Override
	public Hotel get(Integer id) {
		return hotelrepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with the given id not found !!..."));
	}

	public HotelServiceImpl(HotelRepository hotelrepository) {
		super();
		this.hotelrepository = hotelrepository;
	}


}
