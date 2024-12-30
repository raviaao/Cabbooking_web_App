package com.ravitiwari.service;

import com.codewithravi.Request.RideRequest;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;
import com.raviwithravi.Exceptions.DriverException;
import com.raviwithravi.Exceptions.RideException;

public interface RideService {
	
	public Ride requestRide(RideRequest Riderequest, User user)throws DriverException;
	
	public Ride createRideRequest(User user, Driver nearestDriver,
			double picupLatitude,double picupLongitude, double destinationLatitude, double destinationLongitude,
			 String picupArea,String destinationArea);
	
	
	public void acceptRide(Integer rideId)throws RideException;
	
	public void declineRide(Integer rideId, Integer driverId)throws RideException; 
	
	public void startRide(Integer rideId, int otp)throws RideException;
	
	public void completedRide(Integer rideId)throws RideException;
	
	public void canceleRide(Integer rideId)throws RideException;
	
	public Ride findRideById(Integer rideId)throws RideException;

}
