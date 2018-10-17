package com.spring.data.rest.model.projections;

import java.math.BigDecimal;

import org.springframework.data.rest.core.config.Projection;

import com.spring.data.rest.model.Manufacturer;
import com.spring.data.rest.model.Model;
import com.spring.data.rest.model.ModelType;

/**
 * Customizing the payloads when you want the data instead of links to other resources in the 
 * payload as calling different end points will take network bandwidth.
 * All the projection interfaces must be present under model or enity packages or sub-packages.
 * Access Url : http://localhost:8080/api/models?projection=modelDetail
 */

//Type attribute specifies the api end point this projection is going to modify
@Projection(name="modelDetail", types = {Model.class})
public interface ModelDetail {
	
	//Add the fields which you want this projection to contain, the data type and name of fields should match to that of the projection type ie Model class
	String  getName();
	BigDecimal getPrice();
	Manufacturer getManufacturer();
	ModelType getModelType();
	int getFrets();
	String getWoodType();
}
