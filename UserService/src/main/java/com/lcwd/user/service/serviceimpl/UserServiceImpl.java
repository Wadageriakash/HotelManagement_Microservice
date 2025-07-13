package com.lcwd.user.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

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
		ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/rating/user/"+user.getUserId(), ArrayList.class);
		user.setRatings(ratingsOfUser);
		log.info("forObject = {}", ratingsOfUser);
		return user;
	}

}
