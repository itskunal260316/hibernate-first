package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.lti.entity.Account;
import com.lti.entity.AccountTransaction;
import com.lti.util.JPAUtil;

public class AccountDao extends GenericDao {

	public List<AccountTransaction> fetchMiniStatement(int account_no) {
		////// List<?> indicates that list can contain any type
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try {

			String jpql = "select act from AccountTransaction as act "
					+ "where act.account.acno = :acc order by act.dateAndTime desc";// JPQL- query language provided by
																					// ORM
			// Customer=>class in which table resides, cust=>alias which acts as * from sql
			Query q = em.createQuery(jpql);
			q.setParameter("acc", account_no);// acc removes the confusion whether to pass the value from 1 or 0
			q.setMaxResults(5);
			return q.getResultList();

		} finally {
			em.close();
		}
	}
	
	public List<Account> fetchAccounts(String txType, double amount){
		
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try {

			String jpql = "select acc from Account as acc inner join acc.transactions as t"
					+ " where t.amount >= :amt and t.type =:type"; // JPQL- query language provided by
																					// ORM
			// Customer=>class in which table resides, cust=>alias which acts as * from sql
			Query q = em.createQuery(jpql);
			q.setParameter("amt",amount);// acc removes the confusion whether to pass the value from 1 or 0
			q.setParameter("type",txType);
			q.setMaxResults(3);
			
			return q.getResultList();

		} finally {
			em.close();
		}

	}

public List<AccountTransaction> fetchByName(String name){
		
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try {

			String jpql = "select t from AccountTransaction as t join t.account a where a.name = :name";
					 // JPQL- query language provided by
																					// ORM
			// Customer=>class in which table resides, cust=>alias which acts as * from sql
			Query q = em.createQuery(jpql);
			q.setParameter("name",name);// acc removes the confusion whether to pass the value from 1 or 0
			
			
			
			return q.getResultList();

		} finally {
			em.close();
		}

	}

	
	
}