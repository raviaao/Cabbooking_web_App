package com.codewithravi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithravi.Request.DriverSingnupRequest;
import com.codewithravi.Request.LoginRequest;
import com.codewithravi.Request.SignupRequest;
import com.codewithravi.Response.JwtResponse;
import com.codewithravi.config.JwtTokenprovider;
import com.codewithravi.doman.UserRole;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.User;
import com.codewithravi.reposetory.DriverRepository;
import com.codewithravi.reposetory.UserRepository;
import com.ravitiwari.service.CustomUserDetailsService;
import com.ravitiwari.service.DriverService;
import com.raviwithravi.Exceptions.UserException;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DriverRepository driverRepository; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenprovider jwtprovider;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	@Autowired
	private DriverService driverService;
	
	
	@PostMapping("/user/signup")
	public ResponseEntity<JwtResponse> singupHandler(@RequestBody SignupRequest req) throws UserException{
		
		String email = req.getEmail();
		String fullName = req.getFullName();
		String mobail = req.getMobail();
		String password = req.getPassword();
		
		User user=userRepository.findByEmail(email);
		
		if(user!=null)
		{
			throw new UserException("User Already Exist with Email "+ email);
		}
		
		String encodedPassword =passwordEncoder.encode(password);
		
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setPassword(encodedPassword);
		createdUser.setFirstName(fullName);
		createdUser.setMobail(mobail);
		createdUser.setRole(UserRole.USER);
		
		User SavedUser= userRepository.save(createdUser);
		
		Authentication authentication = new 
				UsernamePasswordAuthenticationToken(SavedUser.getEmail(), SavedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtprovider.genrateToken(authentication);
		
		JwtResponse res=new JwtResponse();
		res.setJwt(jwt);
		res.setAuthenticated(true);
		res.setError(false);
		res.setErrorDetails(null);
		res.setType(UserRole.USER);
		res.setMessage("Account created Successfully: "+SavedUser.getFirstName());
		
	
		return new ResponseEntity<JwtResponse>(res,HttpStatus.OK);
		
		
	}
	
	@PostMapping("/driver/signup")
	public ResponseEntity<JwtResponse> driverSignupHandler(@RequestBody DriverSingnupRequest driverSignupRequest){
		
		Driver driver = driverRepository.findByEmail(driverSignupRequest.getEmail());
		
		JwtResponse jwtResponse=new JwtResponse();
		
		if(driver!=null)
		{
			jwtResponse.setAuthenticated(false);
			jwtResponse.setErrorDetails("Email already used with anothe account");
			jwtResponse.setError(true);
			
			return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.BAD_REQUEST);
		}
		
		Driver createdDriver=driverService.registerDrivers(driverSignupRequest);
		
		Authentication authentication = new 
				UsernamePasswordAuthenticationToken(createdDriver.getEmail(), createdDriver.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtprovider.genrateToken(authentication);
		
		jwtResponse.setJwt(jwt);
		jwtResponse.setAuthenticated(true);
		jwtResponse.setError(false);
		jwtResponse.setErrorDetails(null);
		jwtResponse.setType(UserRole.USER);
		jwtResponse.setMessage("Account created Successfully: "+createdDriver.getName());
		
	
		return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.ACCEPTED);
		
		
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<JwtResponse>loginUserHandler(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtprovider.genrateToken(authentication);
		
		JwtResponse res=new JwtResponse();
		res.setJwt(token);
		res.setAuthenticated(true);
		res.setError(false);
		res.setErrorDetails(null);
		res.setType(UserRole.USER);
		res.setMessage("Account Login Successfully");
		
		return new ResponseEntity<JwtResponse>(res,HttpStatus.ACCEPTED);
		
	}
	
	private Authentication authenticate(String username,String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("inviled Username....");
		}
		
		if(passwordEncoder.matches(password,userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password....");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}

	

}
