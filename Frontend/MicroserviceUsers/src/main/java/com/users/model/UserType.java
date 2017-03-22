package com.users.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_type")
public class UserType {
	
	@Id
	@GeneratedValue
	private Integer user_type_id;
	@NotEmpty
	private String user_type_name;
	
	//Constructors
	public UserType() { }

	public UserType(Integer user_type_id, String user_type_name) {
		super();
		this.user_type_id = user_type_id;
		this.user_type_name = user_type_name;
	}

	//Getters & Setters
	public Integer getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(Integer user_type_id) {
		this.user_type_id = user_type_id;
	}

	public String getUser_type_name() {
		return user_type_name;
	}

	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}
}
