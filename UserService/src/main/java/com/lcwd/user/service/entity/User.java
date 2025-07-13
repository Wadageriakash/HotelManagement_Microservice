package com.lcwd.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "micro_users")
@Data
public class User {
	
	@Id
	@Column(name = "Id")
	private String userId;
	@Column(name = "name", length = 20)
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "about")
	private String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	

}
