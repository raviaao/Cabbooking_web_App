package com.codewithravi.Dto;

import com.codewithravi.doman.UserRole;
import com.codewithravi.entity.Vahical;

public class DriverDTO {
	
	private Integer id;
	private String name;
	private String email;
	private String mobail;
	private double rating;
	private double latitude;
	private double longitude;
	private UserRole role;
	private Vahical vehical;
	
	public DriverDTO() {
		
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobail() {
		return mobail;
	}

	public void setMobail(String mobail) {
		this.mobail = mobail;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Vahical getVehical() {
		return vehical;
	}

	public void setVehical(Vahical vehical) {
		this.vehical = vehical;
	}
	
	
	
	
	

}
