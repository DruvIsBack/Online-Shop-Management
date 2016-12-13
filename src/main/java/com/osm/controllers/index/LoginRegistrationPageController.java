package com.osm.controllers.index;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginRegistrationPageController {
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(LoginRegistrationPageController.class);
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	 public ModelAndView loginregisterPage()
	 {
		 return new ModelAndView("login_register");
	 }
}
