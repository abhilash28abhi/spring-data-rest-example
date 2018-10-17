package com.spring.data.rest.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.spring.data.rest.model.Model;
import com.spring.data.rest.model.projections.ModelDetailVirtualView;

@Repository
@RepositoryRestResource(excerptProjection = ModelDetailVirtualView.class)
//excerptProjection will allow to use the projection instead of the models endpoint for the repository
public interface ModelJpaRepository extends JpaRepository<Model, Long> {
	List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal low, BigDecimal high);
	
	List<Model> findByModelTypeNameIn(List<String> types);
	
	@Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
	Page<Model> queryByPriceRangeAndWoodType(@Param("lowest") BigDecimal lowest,
											 @Param("highest") BigDecimal high,
											 @Param("wood") String wood,
											 Pageable page);
	
	List<Model> findAllModelsByType(@Param("name") String name);
}
