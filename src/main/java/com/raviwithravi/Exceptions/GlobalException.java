package com.raviwithravi.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue,WebRequest req){
		
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(DriverException.class)
	public ResponseEntity<ErrorDetails> driverExceptionHandler(DriverException ue,WebRequest req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
	}
	

	@ExceptionHandler(RideException.class)
	public ResponseEntity<ErrorDetails> RideExceptionHandler(RideException ue,WebRequest req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDetails> handleValidationException(ConstraintViolationException ex){
		StringBuilder errorMassege = new StringBuilder();
		
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errorMassege.append(violation.getMessage()+ "\n");
		}
		
		ErrorDetails err=new ErrorDetails(errorMassege.toString(),"ValidationException",LocalDateTime.now());	
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me){
		ErrorDetails err=new ErrorDetails(me.getBindingResult().getFieldError().getDefaultMessage(),"Validation error",LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ue,WebRequest req){
		ErrorDetails err=new ErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.ACCEPTED);
		
	}
	
	
	

}
