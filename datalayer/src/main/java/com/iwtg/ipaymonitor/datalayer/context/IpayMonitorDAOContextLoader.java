package com.iwtg.ipaymonitor.datalayer.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IpayMonitorDAOContextLoader {
		
		private static ApplicationContext context = null;


		public static ApplicationContext contextLoader() {
			if (context == null) {
				context = new ClassPathXmlApplicationContext("classpath*:**/dao-context.xml");
			}
			return context;
		}


}
