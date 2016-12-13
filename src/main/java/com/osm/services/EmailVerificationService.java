package com.osm.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osm.controllers.iofunc.Common;
import com.osm.persistences.EmailVerification;
import com.osm.persistences.User;
import com.osm.repositories.EmailVerificationRepository;

@Service
public class EmailVerificationService{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(EmailVerificationService.class);
	
	@Autowired
	private Common common;
	@Autowired
	private EmailVerificationRepository mailverifyrepo;
	
	public String setEV(User user){
		String code = common.codeGenerate();
		try{
			EmailVerification ev = new EmailVerification();
			ev.setUserid(user.getId());
			ev.setCode(code);
			mailverifyrepo.save(ev);
			return code;			//EV set successfully...
		}catch(Exception e){
			e.printStackTrace();
			return "-1";			//EV can't set...
		}
	}
	
	public EmailVerification getEV(User user){
		if(user != null){
			System.out.println("GetEV("+user.getId()+") entering...");
			List<EmailVerification> list_ev = mailverifyrepo.findByUserID(user.getId());
			System.out.println("Get EV list by userID("+user.getId()+")...");
			System.out.println("Total EV list => "+ list_ev.size());
			if(list_ev.isEmpty()){
				System.out.println("List is empty, return null...");
				return null;		//Not found/exist...
			}else{
				EmailVerification ev = list_ev.get(0);
				System.out.println("Send first row of the fetching list, id of first row is => "+ev.getId()); 
				return list_ev.get(0);
			}
		}else{
			return null;
		}
	}
	
	public Boolean deleteEV(EmailVerification ev){
		try{
			mailverifyrepo.delete(ev);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public String setOrGetEV(User user) {
		System.out.println("setOrGetEV() entering...");
		String code = null;
		if(user != null){
			System.out.println("User not null...");
			EmailVerification ev = getEV(user);
			if(ev != null){
				System.out.println("Fetched EV is not null");
				code = ev.getCode();
				System.out.println("Fetched EV code is => "+code);
				System.out.println("setOrGetEV() exit...");
				return code;
			}else{
				System.out.println("Fetched EV is null");
				System.out.println("calling setEV() for set new code...");
				code = setEV(user);
				System.out.println("New generated code is => "+code);
				return code;
			}
		}else{
			return null;
		}
	}
}
