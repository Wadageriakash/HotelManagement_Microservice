package com.lcwd.user.service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "micro_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	@Transient // This field will not be stored in the database, means this field will be ignored.
	private List<Rating> ratings = new ArrayList<>();

	
	

}
