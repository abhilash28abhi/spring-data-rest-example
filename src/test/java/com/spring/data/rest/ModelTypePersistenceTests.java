package com.spring.data.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

import com.spring.data.rest.model.ModelType;
import com.spring.data.rest.repository.ModelTypeJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ModelTypePersistenceTests {
	@Autowired
	private ModelTypeJpaRepository modelTypeJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		ModelType mt = new ModelType();
		mt.setName("Test Model Type");
		mt = modelTypeJpaRepository.save(mt);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Optional<ModelType> otherModelType = modelTypeJpaRepository.findById(mt.getId());
		assertEquals("Test Model Type", otherModelType.get().getName());
		
		modelTypeJpaRepository.delete(otherModelType.get());
	}

	@Test
	public void testFind() throws Exception {
		Optional<ModelType> mt = modelTypeJpaRepository.findById(1L);
		assertEquals("Dreadnought Acoustic", mt.get().getName());
	}

	@Test
	public void testForNull() throws Exception {
		List<ModelType> mts = modelTypeJpaRepository.findByNameIsNull();
		assertNull(mts.get(0).getName());
	}
}
