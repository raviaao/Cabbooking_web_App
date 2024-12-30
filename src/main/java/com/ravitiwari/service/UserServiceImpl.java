package com.ravitiwari.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.codewithravi.config.JwtTokenprovider;
import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;
import com.codewithravi.reposetory.UserRepository;
import com.raviwithravi.Exceptions.UserException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenprovider jwtProvider;
	
	@Override
	public User getReqUserProfile(String token) throws UserException {
		String email = jwtProvider.getEmailFormToken(token);
		
		User user= userRepository.findByEmail(email);
		
		if(user!=null) {
			
			return user;
		}
		throw new UserException("Invailid Token...");
	}

	@Override
	public User findUserById(Integer Id) throws UserException {
		Optional<User> otp=userRepository.findById(Id);
		
		if(otp.isPresent())
		{
			return otp.get();
		}
		 throw new UserException("User not found Exception"+Id);
	}

	@Override
	public List<Ride> completedRides(Integer userId) throws UserException {
		List<Ride> completedRides=userRepository.getCompletedRides(userId);
		return completedRides;
	}

}
