package com.codewithravi.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Email Should be valid")
	private String email;
	
	private String fullName;
	
	private String password;
	
	private String mobail;

	public SignupRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobail() {
		return mobail;
	}

	public void setMobail(String mobail) {
		this.mobail = mobail;
	}
	
	

}
