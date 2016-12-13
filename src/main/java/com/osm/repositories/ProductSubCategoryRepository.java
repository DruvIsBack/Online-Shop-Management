package com.osm.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.osm.persistences.ProductCategory;
import com.osm.persistences.ProductSubCategory;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Integer>{

	@Query("select subcat from ProductSubCategory subcat order by seq asc")
	public List<ProductSubCategory> getAllBySort();
	
	@Query("select subcat from ProductSubCategory subcat where productCategory_id = ?1 order by seq asc")
	public List<ProductSubCategory> getAllByProductCategoryId(long productCategoryId);
	
	public List<ProductSubCategory> findById(long id);
	
	public List<ProductSubCategory> findByProductCategoryAndId(ProductCategory productCategoryId,long productSubCategoryId);
}	
