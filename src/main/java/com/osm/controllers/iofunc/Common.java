package com.osm.controllers.iofunc;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osm.persistences.User;
import com.osm.services.UserService;

@Component
public class Common{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(Common.class);
	
	@Autowired
	private	UserService userservice;
	
	public String codeGenerate(){
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	public User getSaveUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		User user = (User) session.getAttribute("user");
		if(user != null){   //Have any valid user in session
			user = userservice.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
			System.out.println("User data found in session...");
			return user;
		}else if(cookies != null){			//Have any valid user in cookies
				user = new User();
				for(Cookie cookie : cookies){
					if(cookie.getName().compareTo("username") == 0){
						user.setUsername(cookie.getValue());
					}else if(cookie.getName().compareTo("password") == 0){
						user.setPassword(cookie.getValue());
					}
				}
				user = userservice.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
				if(user != null){
					System.out.println("User data found in cookies...");
					System.out.println("User data set to session...");
					session.setAttribute("user", user);
					return user;
				}
		}
		System.out.println("User data not found cookies or session...");
		return null;
	}
	
	public String genNewNameByDateTime(String ext){
		return ((new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSSS").format(new Date()))+"."+ext).trim();	
	}
}
