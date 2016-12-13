package com.osm.controllers.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.osm.controllers.iofunc.Common;
import com.osm.persistences.User;
import com.osm.services.ProductCategoryService;

@Controller
public class CategoryPageController {
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(CategoryPageController.class);
	
	@Autowired
	private ProductCategoryService serviceProductCategory;
	
	@Autowired
	private Common common;
	
	//Home page
	@RequestMapping(value="/",method=RequestMethod.GET)
	 public ModelAndView categoryPage(HttpServletRequest request, HttpServletResponse response)
	 {
		 ModelAndView  model = new ModelAndView("category");
		 User user = null;
		 user = common.getSaveUser(request);
		 if(user != null){
			 request.setAttribute("user", user);
			 model.addObject("user",user);
		 }else{
			 model.addObject("user",null);
		 }
		 model.addObject("listProductCategory",serviceProductCategory.getAll());
		 return model;
	 }
}
