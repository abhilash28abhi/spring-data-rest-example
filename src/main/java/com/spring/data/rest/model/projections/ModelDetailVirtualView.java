package com.spring.data.rest.model.projections;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.spring.data.rest.model.Model;
import com.spring.data.rest.model.ModelType;

/**
 * This is a virtual projection. Useful when you want to shield the entity property name changes from client requirements.
 * For eg if the payload expects modelName instead of name you can use virtual projection to shield the underneath entity
 * property name and provide your own name. 
 * @author Abhilash
 *
 */

@Projection(name="modelDetailView", types = {Model.class})
public interface ModelDetailVirtualView {
	//target will be the entity ie Model class and name will be the property within Model class, now the method name should not match to that in Model class
	@Value(value="#{target.name}")
	String  getModelName();
	BigDecimal getPrice();
	
	@Value(value="#{target.manufacturer.name}")
	//Manufacturer getManufacturer();
	String getManufacturerName();
	ModelType getModelType();
	int getFrets();
	String getWoodType();
	
	//Combining manufacturer and model name
	@Value(value="#{target.manufacturer.name.split(' ')[0]} #{target.name}")
	String getFullName();
}
