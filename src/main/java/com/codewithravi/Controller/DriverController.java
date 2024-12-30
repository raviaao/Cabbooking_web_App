package com.codewithravi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithravi.entity.Driver;
import com.codewithravi.entity.Ride;
import com.ravitiwari.service.DriverService;
import com.raviwithravi.Exceptions.DriverException;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@GetMapping("/profile")
	public ResponseEntity<Driver>getReqDriverProfileHandler(@RequestHeader("Authorization")String jwt) throws DriverException {
		
		Driver driver=driverService.getReqDriverProfile(jwt);
		
		return new ResponseEntity<Driver>(driver,HttpStatus.OK);
	}
	
	@GetMapping("/{driverId}/current_ride")
	public ResponseEntity<Ride> getDriversCurrentRideHandler(@PathVariable Integer driverId) throws DriverException{
		
		Ride ride=driverService.getDriversCurrentRide(driverId);
		
		return new ResponseEntity<Ride>(ride,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{driverId}/allocated")
	public ResponseEntity<List<Ride>> getAllocatedRidesHandler(@PathVariable Integer driverId) throws DriverException{
		
		List<Ride> rides=driverService.getAllocatedRides(driverId);
		
		return new ResponseEntity<>(rides,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/{driverId}/Completed")
	public ResponseEntity<List<Ride>> getCompletedRidesHandler(@RequestHeader("Authorization")String jwt) throws DriverException{
		
		Driver driver=driverService.getReqDriverProfile(jwt);
		
		List<Ride> rides=driverService.completedRides(driver.getId());
		
		return new ResponseEntity<>(rides,HttpStatus.ACCEPTED);
		
	}

}
