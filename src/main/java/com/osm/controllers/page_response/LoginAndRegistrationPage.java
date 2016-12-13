package com.osm.controllers.page_response;

import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.osm.controllers.iofunc.Common;
import com.osm.controllers.iofunc.FileIO;
import com.osm.persistences.User;
import com.osm.persistences.UserType;
import com.osm.services.EmailVerificationService;
import com.osm.services.GmailServerService;
import com.osm.services.UserService;

@RestController
@RequestMapping(value="login")
public class LoginAndRegistrationPage{
	
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(LoginAndRegistrationPage.class);
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private EmailVerificationService evService;
	
	@Autowired
	private GmailServerService gmailService;
	
	@Autowired
	private FileIO fileIO;
	
	@Autowired
	private Common common;
	
	@RequestMapping(value="clearSession",method=RequestMethod.POST)
	public String clearSession(HttpServletRequest request){
		request.getSession().setAttribute("user", null);
		return "1";
	}
	
	//'0' - Email sent successfully...
	//'-1' - Username or password validation error...
	//'-2' - User already verified....
	//'-3' - Email can't send...
	@RequestMapping(value="resendEmail",method=RequestMethod.POST)
	public @ResponseBody String resendEmail(@RequestParam String username,@RequestParam String password, HttpServletRequest request){
		if(Pattern.matches("[_a-z0-9.-]{6,20}", username) && Pattern.matches("[_a-z0-9.-]{6,20}", password)){
			User user = userservice.getUserByUsernameAndPassword(username, password);
			if(!user.getVerified()){
				String strEmail = getUserConfirmationEmailText(request,user);
				if(gmailService.sentGmail(user.getEmail(), "Email Verification", strEmail)){
					return "0";			//Email sent successfully...
				}else{
					return "-3";		//Email can't send...
				}
			}else{
				return "-2"; 		//User already verified....
			}
		}else{
			return "-1";			//Username or password validation error...
		}
	}
	
	//'0' - User is valid totally...
	//'-1' - User not verify his/her email...
	//'-2' - User not exist...
	//'-3' - Username or password validation error...
	//'-4' - Server error...
	 @RequestMapping(value="dologin",method=RequestMethod.POST)
	 public @ResponseBody String doLogin(Model model,@RequestParam String username, @RequestParam String password, HttpServletRequest request)
	 {
		 System.out.println("GetUser() method called...");
		if(Pattern.matches("[_a-z0-9.-]{6,20}", username) && Pattern.matches("[_a-z0-9.-]{6,20}", password)){
			System.out.println("Validation checked successfull...");
			System.out.println("Try to get user matched in database...");
			User user = new User();
			try{
				System.out.println("....START TRY_CATCH....");
				user = userservice.getUserByUsernameAndPassword(username, password);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("....ERROR....");
				return "-4";
			}finally{
				System.out.println("....COMPLETED TRY_CATCH....");
			}
			if(user != null){
				System.out.println("User found successfully...");
				if(user.getVerified() == true){
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					return "0";		//User is valid totally...
				}else{
					System.out.println("User name => "+user.getUserType().getName());
					return "-1";	//User not verify his/her email...
				}
			}else{
				System.out.println("User not found...");
				return "-2";		//User not exist...
			}
		}else{
			System.out.println("Username validator => "+Pattern.matches("[_a-z0-9.-]{6,20}", password));
			System.out.println("Error in validation...");
			return "-3";		//Username or password validation error...
		}
	 }
	 
	 // '0' - Successfully save user and sent email verification to user mail...
	 // '-1' - Same username existed already....
	 // '-2' - Server problem to create a user...
	 // '-3' - Error occurred due to server file upload reason...
	 // '-4' - User saved and confirmation code generated, but email can't send yet...
	 // '-5' - Can't set/fetch/send email verification Code...
	 // '-6' - Same email already exist...
	 @RequestMapping(value="saveUser",method=RequestMethod.POST)
	 public String SaveUser(@RequestParam("photo_data") MultipartFile photodata, @ModelAttribute User user,HttpServletRequest request) throws AddressException, MessagingException
	 {
		 System.out.println("SaveUser() procedure started...");
		if(userservice.isUserExistByUsername(user.getUsername())){
			return "-1"; //Same username existed already....
		}else if(userservice.isEmailExist(user.getEmail())){
			return "-6";
		}else{
			String photoNameByDateTime = common.genNewNameByDateTime(FilenameUtils.getExtension(photodata.getOriginalFilename()));
			
			if(fileIO.saveFile(request, "/users/pic/", photodata,photoNameByDateTime)){
				UserType ut = new UserType();
				int getTotalUser = userservice.getTotalUser();
				System.out.println("Total user exists => "+getTotalUser);
				if(getTotalUser > 0){
					 ut.setId(3);
					 user.setUserType(ut);
				}else{
					ut.setId(1);
					user.setUserType(ut);
				}
				user.setPhoto(photoNameByDateTime);
				user.setVerified(false);
				user = userservice.saveUser(user);
				if(user != null){
					System.out.println("User saved successfully...");
					String strEmail = getUserConfirmationEmailText(request,user);
					System.out.println("Email text for send user => "+strEmail);
					if(strEmail != "-1"){
						System.out.println("EV set successfully...");
						if(gmailService.sentGmail(user.getEmail(), "Email Verification", strEmail)){
							System.out.println("Email sent successfully...");
						}else{
							System.out.println("Email can't sent...");
							return "-4";		//User saved and confirmation code generated, but email can't send yet...
						}
					}else{
						System.out.println("Can't set/fetch/send email verification Code...");
						return "-5";			//Can't set/fetch/send email verification Code...
					}
				}else{
					System.out.println("Have a problem to create user...");
					return "-2";	//Server problem to create user...
				}
			}else{
				return "-3";   //Error occurred due to server file upload reason...
			}
		}
		 System.out.println("SaveUser() procedure close...");
		return "0";		//Successfully save user and sent email verification to user mail...
	}
	 
	 private String getUserConfirmationEmailText(HttpServletRequest request,User user){
		 System.out.println("getUserConfirmationEmailText() entering...");
		 if(user != null){
			 System.out.println("User not null...");
			 String code = evService.setOrGetEV(user);
			 System.out.println("Get 'code'("+code+") by calling evService.setOrGetEV("+user.getId()+")...");
			 switch(code){
			 	case "-1":
			 			System.out.println("getUserConfirmationEmailText() exit...");
			 			return "-2";	//EV can't set... 
			 	default:
			 		System.out.println("getUserConfirmationEmailText() exit...");
			 		return 
					 "Hi, "+user.getName()+
					 ", \n =============================\n To verify click here => "+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/osm/emailverify?verifycode="+code+"&username="+user.getUsername()+
						" and don't forget Your USERNAME is \""+user.getUsername()+"\" and PASSWORD is \""+user.getPassword()+"\"";
			 }
		 }else{
			 System.out.println("getUserConfirmationEmailText() exit...");
			 return "-1";	//Not a valid user...
		 }
	 }
}
