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
public class SubCategoryPageController{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(SubCategoryPageController.class);
	
	@Autowired
	private Common common;
	
	@Autowired
	private ProductCategoryService serviceProductCategory;
	
	@Autowired
	private ProductSubCategoryService serviceSubProductCategory;
	
	@RequestMapping(value="{categoryId}",method=RequestMethod.GET)
	 public ModelAndView subcategorypage(@PathVariable Long categoryId,HttpServletRequest request){
		System.out.println("Category ID =>"+categoryId);
		ModelAndView  model = new ModelAndView("subcategory");
		User user = null;
		 user = common.getSaveUser(request);
		 if(user != null){
			 request.setAttribute("user", user);
			 model.addObject("user",user);
		 }else{
			 model.addObject("user",null);
		 }
		model.addObject("listProductSubCategory",serviceSubProductCategory.getAllByProductCategoryId(categoryId));
		model.addObject("productCategory",serviceProductCategory.findById(categoryId));
		return model;
	 }
}
