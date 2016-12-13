package com.osm.controllers.index;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.osm.persistences.EmailVerification;
import com.osm.persistences.User;
import com.osm.services.EmailVerificationService;
import com.osm.services.UserService;

@Controller
public class EmailVerificationPageController {
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(EmailVerificationPageController.class);
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private EmailVerificationService evService;
	
	 @RequestMapping(value="emailverify",method=RequestMethod.GET)
	 public ModelAndView emailverify(@RequestParam("verifycode") String code,@RequestParam String username){
		 ModelAndView model = new ModelAndView("email_verify");
		 User user = userservice.getUserByUsername(username);
		 EmailVerification ev = evService.getEV(user);
		 if (ev != null && ev.getCode().equals(code)){
			 System.out.println("Code Matched...");
			 model.addObject("match", false);
			 if(userservice.setVerifyToUser(user)){
				 if(evService.deleteEV(ev)){ 
					 model.addObject("match", true);
				 }
			 }else{
				 System.out.println("Error occured when trying userservice.setVerifyToUser(user)");
			 }
		 }else{
			 System.out.println("Code not matched...");
		 }
		 return model;
	 }
}
