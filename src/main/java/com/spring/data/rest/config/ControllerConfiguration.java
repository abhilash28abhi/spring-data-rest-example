package com.spring.data.rest.config;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring.data.rest.handler.InActiveManufacturerException;

/**
 * Will be used for controller modifications
 * @author Abhilash
 *
 */
@ControllerAdvice
public class ControllerConfiguration {
	
	//Method to send different response code in case of constraint violation exceptions for Manufacturer model
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason= "Invalid data sent to the server")
	public void notValid () {
		
	}
	
	@ExceptionHandler(InActiveManufacturerException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason= "Active flag is not set")
	public void notActiveManufacturer () {
		
	}
}
