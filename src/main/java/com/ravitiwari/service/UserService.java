package com.ravitiwari.service;

import java.util.List;

import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;
import com.raviwithravi.Exceptions.UserException;

public interface UserService {
	
	//public User createdUser(User user)throws UserException;
	
	public User getReqUserProfile(String token)throws UserException;
	
	public User findUserById(Integer Id)throws UserException;
	
	//public User findUserByEmail(String email)throws UserException;
	
	public List<Ride> completedRides(Integer userId)throws UserException;

}
