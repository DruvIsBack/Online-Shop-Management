package com.osm.events;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	//Count active sessions...
	static int totalActiveSessions=0;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalActiveSessions++;
		event.getSession().setMaxInactiveInterval(5*60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		totalActiveSessions--;
		// TODO Auto-generated method stub	
	}
}
	 
