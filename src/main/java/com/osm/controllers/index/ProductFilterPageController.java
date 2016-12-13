package com.osm.controllers.index;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.osm.controllers.iofunc.Common;
import com.osm.persistences.User;
import com.osm.services.ProductCategoryService;
import com.osm.services.ProductSubCategoryService;

@Controller
public class ProductFilterPageController {
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(ProductFilterPageController.class);
	
	@Autowired
	private ProductCategoryService serviceProductCategory;
	
	@Autowired
	private ProductSubCategoryService serviceSubProductCategory;
	
	@Autowired
	private Common common;
	
	@RequestMapping(value="{categoryId}/{subCategoryId}",method=RequestMethod.GET)
	 public ModelAndView productsPage(@PathVariable Long categoryId, @PathVariable Long subCategoryId,HttpServletRequest request)
	 {
		 System.out.println("Category => "+categoryId+", Sub Category => "+subCategoryId);
		 ModelAndView  model = new ModelAndView("ProductsAndFilters");
		 User user = null;
		 user = common.getSaveUser(request);
		 if(user != null){
			 request.setAttribute("user", user);
			 model.addObject("user",user);
		 }else{
			 model.addObject("user",null);
		 }
		 Boolean blnIsInCategory =serviceSubProductCategory.isInCategory(categoryId,subCategoryId);
		 System.out.println("==================================");
		 System.out.println("ERROR => "+blnIsInCategory);
		 System.out.println("==================================");
		 if(blnIsInCategory){
			 model.addObject("productCategory",serviceProductCategory.findById(categoryId));
			 model.addObject("productSubCategory",serviceSubProductCategory.findById(subCategoryId));
		 }else{
			 model.setViewName("InvalidPageRequest");
		 }
		 return model;
	 }
}
