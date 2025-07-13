package com.lcwd.hotel.HotelService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.hotel.entites.Hotel;
import com.lcwd.hotel.repository.HotelRepository;

@SpringBootTest
class HotelServiceApplicationTests {

	 @Autowired
	    private HotelRepository hotelRepository;
	 
	Calculator cal = new Calculator();
	@Test
	void contextLoads() {
	}
	
	@Test
	@Disabled
	void testSum() {
		int expectedresult = 12;
		int actualresult = cal.doSum(2, 10);
		
		assertThat(actualresult).isEqualTo(expectedresult);
	}
	
	@Test
	void testProduct() {
		int expectedresult = 100;
		int actualresult = cal.doProduct(10, 10);
		assertThat(actualresult).isEqualTo(expectedresult);
	}
	
	@Test
	void testComparetwonum() {
		boolean result = cal.comparetwonum(3, 3);
		assertThat(result).isTrue();
	}
	
	
	 @Test
	    void testFindById() {
	        // Create and save a new hotel
	        Hotel newHotel = new Hotel("Aaradhya", "Bengaluru", "Good service");
	        Hotel savedHotel = hotelRepository.save(newHotel);

	        // Check if the hotel exists by ID
	        boolean exists = hotelRepository.existsById(savedHotel.getId());
	        assertTrue(exists, "Hotel should exist in the database");
	    }
	 
	 
	 
	 @AfterEach
	 void tearDown() {
		 System.out.println("tearing down.............");
	 }
	 
	 @BeforeEach
	 void setUp() {
		 System.out.println("settting up..........");
	 }
	
}
