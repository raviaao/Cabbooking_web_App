package com.ravitiwari.service;

import java.util.List;

import com.codewithravi.Request.DriverSingnupRequest;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.Ride;
import com.raviwithravi.Exceptions.DriverException;

public interface DriverService {
	
	public Driver registerDrivers(DriverSingnupRequest driverSingupRequest);
	
	public List<Driver> getAvailableDriver(double pickupLatitude,double pickupLongitude, Ride ride);
	
	public Driver findNearestDriver(List<Driver> availableDrivers,double picupLatitude,double picupLongitude);
	
	public Driver getReqDriverProfile(String jwt) throws DriverException;
	
	public Ride getDriversCurrentRide(Integer driverId) throws DriverException;
	
	public List<Ride> getAllocatedRides(Integer driverId) throws DriverException;
	
	public Driver findDriverById(Integer driverId) throws DriverException;
	
	public List<Ride> completedRides(Integer driverId) throws DriverException;

}
