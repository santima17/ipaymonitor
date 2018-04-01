package com.iwtg.ipaymonitor.bussineslayer.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IpayMonitorBussinesContextLoader {
	
	private static ApplicationContext context = null;


	public static ApplicationContext contextLoader() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:**/bussines-context.xml");
		}
		return context;
	}

}
