package com.lcwd.user.service.service;

import java.util.List;

import com.lcwd.user.service.entity.User;

public interface UserService {

	// create
	User saveUser(User user);

	// get all user
	List<User> getAllUser();

	// get single user of the given user id
	User getUser(String userId);

	// delete user
	
	// update user
}
