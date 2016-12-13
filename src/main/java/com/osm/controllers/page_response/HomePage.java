package com.osm.controllers.page_response;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.osm.controllers.iofunc.FileIO;
import com.osm.services.ProductCategoryService;
import com.osm.services.ProductSubCategoryService;

@RestController
@RequestMapping(value="/")
public class HomePage {
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(HomePage.class);
	
	@Autowired
	private FileIO fileIO;
	
	@Autowired
	private ProductCategoryService serviceProductCategory;
	
	@Autowired
	private ProductSubCategoryService serviceProductSubCategory;
	
	
	@RequestMapping(value="uploads/productCategories/{imageId}", method=RequestMethod.GET)
	public byte[] getPhotoOfProductCategory(@PathVariable Long imageId, HttpServletRequest request) throws IOException{
		String imageName = serviceProductCategory.findById(imageId).getPic();
		return fileIO.getFile("/productCategories/"+imageName, request,true);
	}
	@RequestMapping(value="uploads/productSubCategories/{productSubCategoryId}", method=RequestMethod.GET)
	public byte[] getPhotoOfProductSubCategory(@PathVariable Long productSubCategoryId, HttpServletRequest request) throws IOException{
		String imageName = serviceProductSubCategory.findById(productSubCategoryId).getPic();
		return fileIO.getFile("/productSubCategories/"+imageName, request,true);
	}
}
