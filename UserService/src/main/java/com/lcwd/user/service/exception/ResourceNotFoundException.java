package com.lcwd.user.service.exception;

import org.springframework.stereotype.Component;

@Component
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource not found onn server !!..");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
