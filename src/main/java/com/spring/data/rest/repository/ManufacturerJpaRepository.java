package com.spring.data.rest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.spring.data.rest.model.Manufacturer;

@Repository
@RepositoryRestResource(path ="mfgs", collectionResourceRel = "mfgs")
//collectionResourceRel attribute will set the name under _embedded as same as the api path now
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {
	List<Manufacturer> findByFoundedDateBefore(Date date);
	List<Manufacturer> findByActiveTrue();
	List<Manufacturer> findByActiveFalse();
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	List<Manufacturer> findAll();
}
