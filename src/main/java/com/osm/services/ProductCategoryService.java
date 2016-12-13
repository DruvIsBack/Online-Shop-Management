package com.osm.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osm.persistences.ProductCategory;
import com.osm.repositories.ProductCategoryRepository;

@Service
@Transactional
public class ProductCategoryService{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(ProductCategoryService.class);
	
	@Autowired
	private ProductCategoryRepository productCategoryRepo;
	
	public List<ProductCategory> getAll(){
		List<ProductCategory> pc = productCategoryRepo.getAllBySort();
		System.out.println("Total category found =>"+pc.size());
		for(ProductCategory temp : pc){
			System.out.println("Name => "+temp.getName()+", Sequence =>"+temp.getSeq());
		}
		return pc;
	}
	
	public ProductCategory findById(Long categoryId){
		return productCategoryRepo.findById(categoryId);
	}
}
