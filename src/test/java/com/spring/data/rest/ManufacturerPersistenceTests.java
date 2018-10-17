package com.spring.data.rest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.data.rest.model.Manufacturer;
import com.spring.data.rest.repository.ManufacturerJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ManufacturerPersistenceTests {

	@Autowired
	private ManufacturerJpaRepository manufacturerJpaRepository;


	@Test
	public void testTrueFalse() throws Exception {
		List<Manufacturer> mans = manufacturerJpaRepository.findByActiveTrue();
		assertEquals("Fender Musical Instruments Corporation", mans.get(0).getName());

		mans = manufacturerJpaRepository.findByActiveFalse();
		assertEquals("Gibson Guitar Corporation", mans.get(0).getName());
	}
}
