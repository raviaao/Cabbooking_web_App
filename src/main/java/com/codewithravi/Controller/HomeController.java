package com.codewithravi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithravi.Response.MassageResponse;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public ResponseEntity<MassageResponse> homeController(){
		
		MassageResponse msg=new MassageResponse("Welcome Ola Backend System");
		
		return new ResponseEntity<MassageResponse>(msg,HttpStatus.ACCEPTED);
	}

}
