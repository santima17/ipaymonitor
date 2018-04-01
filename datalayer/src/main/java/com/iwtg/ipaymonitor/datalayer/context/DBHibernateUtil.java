package com.iwtg.ipaymonitor.datalayer.context;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBHibernateUtil {
	

	private static final SessionFactory sessionFactoryMain;


	static {
		try {
			sessionFactoryMain = new Configuration().configure("ipay_hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactoryGenerator creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	

	public static Session getSessionFactoryMain() {
		return sessionFactoryMain.openSession();
}

}
