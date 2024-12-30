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

import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;
import com.ravitiwari.service.UserService;
import com.raviwithravi.Exceptions.UserException;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer userId) throws UserException{
		System.out.println("find by User Id");
		User User = userService.findUserById(userId);
	
		return new ResponseEntity<User>(User,HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getReqUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.getReqUserProfile(jwt);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/rides/completed")
	public ResponseEntity<List<Ride>> getcompletedRidesHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user = userService.getReqUserProfile(jwt);
		List<Ride> rides= userService.completedRides(user.getId());
		return new ResponseEntity<>(rides,HttpStatus.ACCEPTED);	
	}
	
	
	
	
	
	
	

}
