package com.osm.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osm.persistences.User;
import com.osm.repositories.UserRepository;

@Service
@Transactional
public class UserService{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(UserService.class);
	
	@Autowired
	UserRepository userrepository;
	
	public Boolean isEmailExist(String email){
		List<User> li_user = userrepository.findByEmail(email);
		if(li_user.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	public Boolean setVerifyToUser(User user){
		try{
			userrepository.setVerifyToUser(user.getId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public User getUserByUsernameAndPassword(String username, String password){
		List<User> list_user = new ArrayList<User>();
		if(username != null || password != null){
			list_user =userrepository.findByUsernameAndPassword(username, password);
		}else{
			list_user =userrepository.findByUsernameAndPassword("", "");
		}
		
		if(list_user.isEmpty()){
			return null;
		}else{
			return list_user.get(0);
		}
	}
	
	public User saveUser(User user){
		return userrepository.save(user);
	}
	
	public Boolean isUserExistByUsername(String username){
		if(userrepository.getCountUsername(username) == 1){
			return true;
		}else{
			return false;
		}
	}
	public User getUserByUsername(String username){
		return userrepository.findByUsername(username);
	}
	public int getTotalUser(){
		return userrepository.findAll().size();
	}
}
