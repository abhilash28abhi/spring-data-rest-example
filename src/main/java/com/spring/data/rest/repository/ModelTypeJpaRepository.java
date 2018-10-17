package com.spring.data.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.data.rest.model.ModelType;

@Repository
public interface ModelTypeJpaRepository extends JpaRepository<ModelType, Long> {
	List<ModelType> findByNameIsNull();
}
