package com.lti.dao;

import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import com.lti.entity.Account;
import com.lti.util.JPAUtil;

//persistence logic(logic to persist the data in the database)
public class GenericDao {
	// code to insert customer data in the database using ORM
	public void store(Object obj) {

		// STEP- 1 = Create/Obtain EntityManagerFactory object and provides the entity
		// manager instance to the entity manager
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();//// reads persistence.xml
		// emf object should be created only once

		// STEP-2 = Create EntityManager Object
		EntityManager em = emf.createEntityManager();///// persistence object
		// entity manager will forward the object received from the EMFO to the database

		// STEP-3 = Start or participate in a transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// now we can insert update delete select whatever we want

		em.merge(obj); // merge method is 2 in 1 method
		// for transient=>insert
		// for detached=>update

		// sometimes when the database is opend then the auto commit flag is seto off
		// and thus we ,
		tx.commit();

		// should be in a finally block
		em.close();
	}

	// we do not use the object becuase
	// Employee e = (Employee)dao.fetchById(Customer.class, 123)
	public <E> E fetchById(Class<E> classname, Object pk) { // <E> is generic class
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try {
			E e = em.find(classname, pk);
			return e;
		} finally {
			em.close();
		}
	}

	public <E> List<?> fetchAll(Class<E> clazz) {
		////// List<?> indicates that list can contain any type
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try {

			Query q = em.createQuery("select obj from" + clazz.getName() + "as obj");// JPQL- query language provided by
																						// ORM
			// Customer=>class in which table resides, cust=>alias which acts as * from sql
			return q.getResultList();

		} finally {
			em.close();
		}

	}

}
