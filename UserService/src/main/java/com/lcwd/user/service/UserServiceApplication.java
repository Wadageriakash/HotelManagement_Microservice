package com.lcwd.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
public class UserServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		System.out.println("Akash Wadageri");
	}

}
