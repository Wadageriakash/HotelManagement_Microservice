package com.lcwd.hotel.HotelService;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lcwd.hotel.hotelserviceImpl.HotelServiceImpl;
import com.lcwd.hotel.repository.HotelRepository;

@ExtendWith(MockitoExtension.class)
public class HotelserviceTest {

	@Mock
	HotelRepository hotelrepository;
	
	HotelServiceImpl hotelserviceImpl;
	
	@BeforeEach
	void setUp() {
		this.hotelserviceImpl = new HotelServiceImpl(this.hotelrepository);
	}
	@Test
	 void getAllhotelstest(){
		hotelserviceImpl.getAll();
		verify(hotelrepository).findAll();
	 }
	
}
