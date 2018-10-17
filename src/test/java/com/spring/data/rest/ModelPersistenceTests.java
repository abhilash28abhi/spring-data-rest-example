package com.spring.data.rest;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.data.rest.model.Model;
import com.spring.data.rest.repository.ModelJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ModelPersistenceTests {

	@Autowired
	private ModelJpaRepository modelJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Model m = new Model();
		m.setFrets(10);
		m.setName("Test Model");
		m.setPrice(BigDecimal.valueOf(55L));
		m.setWoodType("Maple");
		m.setYearFirstMade(new Date());
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();
	}

	@Test
	public void testGetModelsByTypes() throws Exception {
		List<String> types = new ArrayList<String>();
		types.add("Electric");
		types.add("Acoustic");
		List<Model> mods = modelJpaRepository.findByModelTypeNameIn(types);
		
		mods.forEach((model) -> {
			assertTrue(model.getModelType().getName().equals("Electric") || 
					model.getModelType().getName().equals("Acoustic"));
		});
	}
}
