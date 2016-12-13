package com.osm.services;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osm.persistences.ProductCategory;
import com.osm.persistences.ProductSubCategory;
import com.osm.repositories.ProductSubCategoryRepository;

@Service
@Transactional
public class ProductSubCategoryService{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(ProductSubCategoryService.class);
	
	@Autowired
	private ProductSubCategoryRepository productSubCategoryRepo;
	
	public List<ProductSubCategory> getAllByProductCategoryId(Long categoryId){
		List<ProductSubCategory> lpsc = productSubCategoryRepo.getAllByProductCategoryId(categoryId);
		System.out.println("In category "+categoryId+". Total SubCategory found=> "+lpsc.size());
		if(lpsc.size()>0){
			return lpsc;
		}else {
			return null;
		}
	}

	public ProductSubCategory findById(Long subCategoryId) {
		List<ProductSubCategory> lpsc = productSubCategoryRepo.findById(subCategoryId);
		if(lpsc.size()>0){
			return lpsc.get(0);
		}else{
			return null;
		}
	}

	public Boolean isInCategory(Long categoryId, Long subCategoryId) {
		System.out.println("isInCategory() started...................");
		System.out.println("CategoryID => "+categoryId+", SubCategoryId =>"+subCategoryId);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(categoryId);
		int total_found = productSubCategoryRepo.findByProductCategoryAndId(productCategory, subCategoryId).size();
		if(total_found ==1){
			return true;
		}else{
			return false;
		}
	}
}
