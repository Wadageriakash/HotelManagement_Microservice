package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	// create
	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	// Here fallbackMethod is for, whenever the rating or Hotel service is down then  will invoke the fallbackMethod 
	// get single user
	@GetMapping("/{userId}")
	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod ="ratingHotelFallback")
	public ResponseEntity<User> getSinglUser(@PathVariable String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	// creating fall back method for the circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		log.info("Fallback is executed because service is down : ", ex.getMessage());
		User user = User.builder()
		.email("dummy@gmail.com")
		.name("Dummy")
		.about("This user is created dummy because some service is down")
		.userId("1412347")
		.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	// get all user
	@GetMapping("/alluser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	
	
	
}
