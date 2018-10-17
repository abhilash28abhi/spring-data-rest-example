package com.spring.data.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.data.rest.model.Location;
import com.spring.data.rest.repository.LocationJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LocationPersistenceTests {
	@Autowired
	private LocationJpaRepository locationJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void testJpaFind() {
		List<Location> locations = locationJpaRepository.findAll();
		assertNotNull(locations);
	}
	
	@Test
	public void testJpaAnd() {
		List<Location> locations = locationJpaRepository.findByStateNot("Utah");
		assertNotNull(locations);
		
		assertNotSame("Utah", locations.get(0).getState());
	}

	@Test
	public void testJpaOr() {
		List<Location> locations = locationJpaRepository.findByStateIsOrCountryEquals("Utah", "Utah");
		assertNotNull(locations);
		
		assertEquals("Utah", locations.get(0).getState());
	}

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Location location = new Location();
		location.setCountry("Canada");
		location.setState("British Columbia");
		location = locationJpaRepository.saveAndFlush(location);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Optional<Location> otherLocation = locationJpaRepository.findById(location.getId());
		assertEquals("Canada", otherLocation.get().getCountry());
		assertEquals("British Columbia", otherLocation.get().getState());
		
		//delete BC location now
		locationJpaRepository.delete(otherLocation.get());
	}

	@Test
	public void testFindWithLike() throws Exception {
		List<Location> locs = locationJpaRepository.findByStateIgnoreCaseStartingWith("new");
		assertEquals(4, locs.size());

		locs = locationJpaRepository.findByStateNotLikeOrderByStateAsc("New%");
		assertEquals(46, locs.size());
		
		locs.forEach((location) -> {
			System.out.println(location.getState());
		});
		
		Location loc = locationJpaRepository.findFirstByStateIgnoreCaseStartingWith("a");
		assertEquals("Alabama", loc.getState());
	}

	@Test
	@Transactional  //note this is needed because we will get a lazy load exception unless we are in a tx
	public void testFindWithChildren() throws Exception {
		Optional<Location> arizona = locationJpaRepository.findById(3L);
		assertEquals("United States", arizona.get().getCountry());
		assertEquals("Arizona", arizona.get().getState());
		
		assertEquals(1, arizona.get().getManufacturers().size());
		
		assertEquals("Fender Musical Instruments Corporation", arizona.get().getManufacturers().get(0).getName());
	}
}
