package com.pcms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.context.ContextLoaderListener;

public class InitListener extends ContextLoaderListener{
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event)
	{	
		super.contextInitialized(event);
		ServletContext application = event.getServletContext();
		PropertyConfigurator.configure(application.getRealPath("/")+"WEB-INF/config/log4j.properties");
	}
}
