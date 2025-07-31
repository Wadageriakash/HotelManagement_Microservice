package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
//	int retryCount = 1;
	// get single user
	@GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod ="circuitBreakerFallback")
    @Retry(name="ratingHotelService", fallbackMethod = "retryFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        // log.info("Retry count: {} ", retryCount); 
        // retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // Fallback for Circuit Breaker
    public ResponseEntity<User> circuitBreakerFallback(String userId, Exception ex){
        log.warn("Circuit Breaker opened for rating/hotel service for userId: {}. Exception: {}", userId, ex.getMessage());
        User user = User.builder()
            .email("circuitbreaker@gmail.com")
            .name("Service Unavailable")
            .about("This user data is degraded due to rating/hotel service being unavailable.")
            .userId("9999999") 
            .build();
        return new ResponseEntity<>(user, HttpStatus.SERVICE_UNAVAILABLE);
    }

    // Fallback for Retry
    public ResponseEntity<User> retryFallback(String userId, Exception ex){
        log.warn("All retries failed for rating/hotel service for userId: {}. Exception: {}", userId, ex.getMessage());
        User user = User.builder()
            .email("retryfailed@gmail.com")
            .name("Temporary Issue")
            .about("Could not retrieve full user data due to a temporary issue with external services after multiple retries.")
            .userId("8888888") 
            .build();
        return new ResponseEntity<>(user, HttpStatus.OK); 
    }

    // Fallback for Rate Limiter
    public ResponseEntity<User> rateLimiterFallback(String userId, Exception ex){
        log.warn("Rate limit exceeded for userId: {}. Exception: {}", userId, ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Rate-Limit-Remaining", "0");
        headers.add("Retry-After", "60"); 
        return new ResponseEntity<>(null, headers, HttpStatus.TOO_MANY_REQUESTS);
    }
	
	
	// get all user
	@GetMapping("/alluser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	
	
	
}
