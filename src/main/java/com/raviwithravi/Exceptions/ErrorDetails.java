package com.raviwithravi.Exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
	
	private String error;
	private String Details;
	private LocalDateTime timestamp;
	public ErrorDetails(String error, String details, LocalDateTime timestamp) {
		super();
		this.error = error;
		Details = details;
		this.timestamp = timestamp;
	}
	
	
	
	
}