package com.osm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.osm.persistences.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

	@Query("select cat from ProductCategory cat order by seq asc")
	public List<ProductCategory> getAllBySort();
	
	public ProductCategory findById(long id);
}	
