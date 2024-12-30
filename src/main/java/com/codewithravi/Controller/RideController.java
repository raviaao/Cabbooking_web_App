package com.codewithravi.Controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithravi.Dto.RideDto;
import com.codewithravi.Dto.Mapper.DtoMapper;
import com.codewithravi.Request.RideRequest;
import com.codewithravi.Request.StartRideRequest;
import com.codewithravi.Response.MassageResponse;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;
import com.codewithravi.reposetory.RideRepository;
import com.ravitiwari.service.DriverService;
import com.ravitiwari.service.RideService;
import com.ravitiwari.service.UserService;
import com.raviwithravi.Exceptions.DriverException;
import com.raviwithravi.Exceptions.RideException;
import com.raviwithravi.Exceptions.UserException;

@RestController
@RequestMapping("/api/rides")
public class RideController  {
	
	@Autowired
	private RideService rideService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/request")
	public ResponseEntity<RideDto> userRequestRideHandler(@RequestBody RideRequest rideRequest, @RequestHeader("Authorization") String jwt) throws UserException, DriverException
	{
		User user = userService.getReqUserProfile(jwt);
		
		Ride ride = rideService.requestRide(rideRequest, user);
		
		RideDto rideDto=DtoMapper.toRideDto(ride);
		
		return new ResponseEntity<>(rideDto,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/{ride}/accept")
	public ResponseEntity<MassageResponse> acceptRideHandler(@PathVariable Integer rideId) throws RideException {
		
		rideService.acceptRide(rideId);
		MassageResponse res=new MassageResponse("Ride Accepted By Driver");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}


	@PutMapping("/{ride}/decline")
	public ResponseEntity<MassageResponse> declineRideHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer rideId) throws DriverException, RideException{
		
		Driver driver= driverService.getReqDriverProfile(jwt);
		
		rideService.declineRide(rideId,driver.getId());
		
		MassageResponse res=new MassageResponse("Ride Decline By Driver");
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("{ride}/start")
	public ResponseEntity<MassageResponse> rideStartHandler(@PathVariable Integer rideId,@RequestBody StartRideRequest req) throws RideException{
		
		rideService.startRide(rideId,req.getOtp());
		
		MassageResponse res=new MassageResponse("Ride is Started");
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);	
	}
	
	
	@PutMapping("{ride}/complete")
	public ResponseEntity<MassageResponse> rideCompleteHandler(@PathVariable Integer rideId) throws RideException{
		
		rideService.completedRide(rideId);
		
		MassageResponse res=new MassageResponse("Ride is Completed Thank You For Booking");
		
		
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping("/{rideId}")
	public ResponseEntity<RideDto> findRideByHandler(@PathVariable Integer rideId, @RequestHeader("Authorization")String Jwt) throws UserException, RideException{
		
		User user = userService.getReqUserProfile(Jwt);
		Ride ride = rideService.findRideById(rideId);
		RideDto rideDto=DtoMapper.toRideDto(ride);
		return new ResponseEntity<RideDto>(rideDto,HttpStatus.ACCEPTED);
	}
	
	
	
	

}
