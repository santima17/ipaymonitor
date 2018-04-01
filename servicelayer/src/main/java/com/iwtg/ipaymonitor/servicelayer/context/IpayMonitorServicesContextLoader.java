package com.iwtg.ipaymonitor.servicelayer.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IpayMonitorServicesContextLoader {
	
	private static ApplicationContext context = null;


	public static ApplicationContext contextLoader() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:**/services-context.xml");
		}
		return context;
	}


}
