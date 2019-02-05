package com.lti.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf=null;
	
	static {
		emf  = Persistence.createEntityManagerFactory("hibernate-oracle");
	}

	
	public static EntityManagerFactory getEntityManagerFactory() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			//thread that is executed on jvm shutdown
			public void run() {
				emf.close();//closing the singleton object before shutdown of jvm
			}
		});
		
		return emf;
	}
	
	 
	/*public static EntityManagerFactory getEntityManagerFactory() {
		//When this line of code will execute persistance.xml will be read
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("hibernate-oracle");//persistance-unit name
		return emf;*/
	
	}
	

