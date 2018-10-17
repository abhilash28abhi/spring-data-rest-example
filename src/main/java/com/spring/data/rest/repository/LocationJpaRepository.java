package com.spring.data.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.spring.data.rest.model.Location;

@Repository
//This will not expose this repository for REST along with any relationship which lies on this repo in this case the headquaters in manufacturers model
@RepositoryRestResource(exported = false)
public interface LocationJpaRepository extends JpaRepository<Location, Long> {
	List<Location> findByStateIgnoreCaseStartingWith(String stateName);
	Location findFirstByStateIgnoreCaseStartingWith(String stateName);
	List<Location> findByStateNotLikeOrderByStateAsc(String stateName);
	
	List<Location> findByStateIsOrCountryEquals(String value, String value2);
	List<Location> findByStateNot(String state);
	
}
