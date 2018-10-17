package com.spring.data.rest.handler;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.spring.data.rest.model.Manufacturer;

@Component
//This tells spring data rest that it will listen to events occurring in Manufacturer model	
@RepositoryEventHandler(Manufacturer.class)
public class ManufacturerEventHandler {
	
	//This method will be called before the model is saved to the DB
	@HandleBeforeCreate
	//We can secure various CRUD operations on the repo using authorization
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void handleBeforeCreate(Manufacturer manufacturer) {
		//can perform logic or validations here like
		//do checks to see if name is valid manufacturer etc
		//only allow new manufacturers to create in active state
		if (!manufacturer.getActive()) {
			throw new InActiveManufacturerException("New Manufacturers must be active");
		}
	}
}
