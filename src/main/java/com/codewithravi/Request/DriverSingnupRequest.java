package com.codewithravi.Request;

import com.codewithravi.entity.License;
import com.codewithravi.entity.Vahical;

public class DriverSingnupRequest {
	
	private String name;
	private String email;
	private String mobail;
	private String password;
	private double latitude;
	private double longitude;
	private License license;
	private Vahical vehical;

	public DriverSingnupRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	public Vahical getVehical() {
		return vehical;
	}
	public void setVehical(Vahical vehical) {
		this.vehical = vehical;
	}
	
	

}
