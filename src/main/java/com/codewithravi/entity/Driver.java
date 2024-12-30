package com.codewithravi.entity;

import java.util.List;

import com.codewithravi.doman.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String name;
	
	private String email;
	
	private String mobail;
	
	private double rating;
	
	private double latitude;
	
	private double longitude;
	
	private UserRole role;
	
	private String password;
	
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
	private License license;
	
	@JsonIgnore
	@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Ride> rides;
	
	@OneToMany(mappedBy = "driver",cascade = CascadeType.ALL,orphanRemoval = true)
	private Vahical vahical;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private Ride currentRide;
	
	private Integer totalRevenue=0;

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public Vahical getVahical() {
		return vahical;
	}

	public void setVahical(Vahical vahical) {
		this.vahical = vahical;
	}

	public Ride getCurrentRide() {
		return currentRide;
	}

	public void setCurrentRide(Ride currentRide) {
		this.currentRide = currentRide;
	}

	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	
	
	
	
	
	
	
}
