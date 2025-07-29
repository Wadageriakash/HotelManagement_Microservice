package com.lcwd.user.service.serviceimpl;



import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.service.HotelService;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;
	

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// get user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !! " + userId));
		// http://localhost:8083/rating/user/d8193399-b004-444d-bacb-42822eba065f
		// fetch rating of the above user from RATING SERVICE
//		ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/rating/user/"+user.getUserId(), ArrayList.class);
//		user.setRatings(ratingsOfUser);
//		log.info("forObject = {}", ratingsOfUser);
		
//		Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/rating/user/"+user.getUserId(), Rating[].class);
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(), Rating[].class);

		log.info("forObject = {}", ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			// using the RestTemplate..........................
			// api call to hotel service to get the hotel
			// set the hotel to rating
			// return the rating
			// http://localhost:8082/hotels/1
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
//			log.info("Response status code:{} ", forEntity.getStatusCode());
//			rating.setHotel(hotel);
//			return rating;
//		}).collect(Collectors.toList());
//			user.setRatings(ratingList);
		
		//using the Feign Client................... 
		
		Hotel hotel = hotelService.getHotel(rating.getHotelId());
		rating.setHotel(hotel);
		return rating;
	}).collect(Collectors.toList());
		user.setRatings(ratingList);
		
		return user;
	}

}
