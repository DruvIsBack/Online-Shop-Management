package com.osm.services;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.osm.persistences.GmailServer;
import com.osm.repositories.GmailServerRepository;

@Service
public class GmailServerService{
	@SuppressWarnings("unused")
	private Logger logger=Logger.getLogger(GmailServerService.class);
	
	private Session session;
	
	@Autowired
	private GmailServerRepository GmailServerRepository;
	
	private GmailServer getMailServer(){
		return GmailServerRepository.findAll().get(0);
	}
	
	private void setNewGmailSession(){
		//if(session == null){
			System.out.println("try to generate new Gmail session...");
			GmailServer gs = null;
			try{
				gs = getMailServer();
			}catch(Exception e){
				e.printStackTrace();
				gs = null;
				System.out.println("Gmail data not found in database");
			}
			String username = gs.getUsername();
			String password = gs.getPassword();
			try{
				Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
				  // Get a Properties object
				     Properties props = System.getProperties();
				     props.setProperty("mail.smtp.host", "smtp.gmail.com");
				     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
				     props.setProperty("mail.smtp.socketFactory.fallback", "false");
				     props.setProperty("mail.smtp.port", "465");
				     props.setProperty("mail.smtp.socketFactory.port", "465");
				     props.put("mail.smtp.auth", "true");
				     props.put("mail.debug", "true");
				     props.put("mail.store.protocol", "pop3");
				     props.put("mail.transport.protocol", "smtp");
				    	 session = Session.getDefaultInstance(props, 
				                          new Authenticator(){
				                             protected PasswordAuthentication getPasswordAuthentication() {
				                                return new PasswordAuthentication(username+"@gmail.com", password);
				                             }});
			}catch(Exception e){
				session = null;
				e.printStackTrace();
			}finally{
				if(session != null){
					System.out.println("Gmail session created successfully...");
				}else{
					System.out.println("Gmail session creation failed...");
				}
			}
		//}else{
			//System.out.println("Gmail session already created...");
		//}
	}
	public Boolean sentGmail(String email,String title, String msg){
		setNewGmailSession();
		try{
			   MimeMessage message = new MimeMessage(session);  
			   message.setFrom(new InternetAddress(email));//change accordingly  
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));  
			   message.setSubject(title);  
			   message.setText(msg);
			   message.setSentDate(new Date());
			   Transport.send(message);
			   System.out.println("Message sent successfully...");  
			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Message can't sent...");
			return false;
		}
	}
}
