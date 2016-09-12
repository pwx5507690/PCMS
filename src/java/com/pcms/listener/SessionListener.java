package com.pcms.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionListener implements HttpSessionListener {
	
	private static final Log LOG = LogFactory.getLog(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {		  
	  	 
	}
}
