package com.codewithravi.entity;

import com.codewithravi.doman.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(unique = true)
	private String firstName;
	
	private String password;
	
	private String email;
	
	@Column(unique = true)
	private String Mobail;
	
	private String profilepicture;
	
	private UserRole Role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	

	public User(Integer id, String firstName, String password, String email, String mobail, String profilepicture,
			UserRole role) {
		super();
		Id = id;
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		Mobail = mobail;
		this.profilepicture = profilepicture;
		Role = role;
	}



	public Integer getId() {
		return Id;
	}



	public void setId(Integer id) {
		Id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMobail() {
		return Mobail;
	}



	public void setMobail(String mobail) {
		Mobail = mobail;
	}



	public String getProfilepicture() {
		return profilepicture;
	}



	public void setProfilepicture(String profilepicture) {
		this.profilepicture = profilepicture;
	}



	public UserRole getRole() {
		return Role;
	}



	public void setRole(UserRole role) {
		Role = role;
	}



	

}
