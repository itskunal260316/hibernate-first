package com.lti.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManagerFactory getEntityManagerFactory() {
		//When this line of code will execute persistance.xml will be read
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("hibernate-oracle");//persistance-unit name
		return emf;
	
	}
	
}
