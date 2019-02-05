package com.lti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.lti.dao.AccountDao;
import com.lti.entity.Account;
import com.lti.entity.AccountTransaction;
import com.lti.util.JPAUtil;

/////////////ACCOPEN////////ACCOPEN///////////////ACCOPEN///////////////ACCOPEN/////////////ACCOPEN
//business logic for deposit,withdraw,etc.
public class AccountService {

	public void openAccount(String name, String type, double balance) {
		Account acc = new Account();
		acc.setName(name);
		acc.setType(type);
		acc.setBalance(balance);

		AccountDao dao = new AccountDao();
		dao.store(acc);
	}

	//////////// WITHDRAW//////////WITHDRAW//////////WITHDRAW////////////////WITHDRAW/////////////

	public void withdraw(int account_no, double amount) {

		AccountDao dao = new AccountDao();
		Account ac = dao.fetchById(Account.class, account_no);
		AccountTransaction at = new AccountTransaction();
		// AccountTransaction at = new AccountTransaction();
		// at.setAmount(amount);
		// double amt = at.getAmount();
		// double bal = acc.getBalance();
		if (ac.getBalance() > amount) {

			double bal = ac.getBalance();
			bal = bal - amount;
			ac.setBalance(bal);
		} else
			System.out.println("The Balance is insufficient");

		at.setAccount(ac);

		at.setAmount(amount);
		at.setType("withdraw");
		at.setDateAndTime(new Date());
		dao.store(at);
		dao.store(ac);
	}

	///////////////// DEPOSIT//////////DEPOSIT////////////////////////DEPOSIT/////////////////DEPOSIT///////////////

	public void deposit(int account_no, double amount) {
		AccountDao dao = new AccountDao();
		Account ac = dao.fetchById(Account.class, account_no);
		AccountTransaction at = new AccountTransaction();
		double bal = ac.getBalance();
		bal = bal + amount;
		ac.setBalance(bal);
		at.setAccount(ac);
		at.setAmount(amount);
		at.setType("Deposit");
		at.setDateAndTime(new Date());
		dao.store(at);
		dao.store(ac);
	}

	/////////// BALANCE/////BALANCE//////////////BALANCE////////////////BALANCE////////////BALANCE//////

	public void balance(int account_no) {
		AccountDao dao = new AccountDao();
		Account ac = dao.fetchById(Account.class, account_no);
		System.out.println(ac.getBalance());

	}

	///////////////// TRANSFER////////////////////////TRANSFER////////////////////////////TRANSFER////////////TRANSFER
	public void transfer(int from, int to, double amount) {
		AccountDao dao = new AccountDao();
		Account ac = dao.fetchById(Account.class, from);
		Account ac1 = dao.fetchById(Account.class, to);
		AccountTransaction at = new AccountTransaction();
		AccountTransaction at1 = new AccountTransaction();
		double bal = ac.getBalance();
		double bal1 = ac1.getBalance();
		if (bal > amount) {
			bal = bal - amount;
			ac.setBalance(bal);
			bal1 = bal1 + amount;
			ac1.setBalance(bal1);
		} else
			System.out.println("Check balance");
		// for the entry in the tracct table for the from waala person
		at.setAccount(ac);
		at.setAmount(amount);
		at.setDateAndTime(new Date());
		at.setType("Debit");

		// for the entry in the tracct table for the to waala person
		at1.setAccount(ac);
		at1.setAmount(amount);
		at1.setDateAndTime(new Date());
		at1.setType("Credit");

		dao.store(at);
		dao.store(at1);
		dao.store(ac);
		dao.store(ac1);
	}

	///////////////////// MINISTATEMENT////////////MINISTATEMENT//////////////////////MINISTATEMENT//////////////////////

	public List<AccountTransaction> ministatement(int account_no) {
		AccountDao dao = new AccountDao();

		List<AccountTransaction> list = dao.fetchMiniStatement(account_no);

		for (AccountTransaction a : list) {
			System.out.println(a.getTxno());
		}
		return dao.fetchMiniStatement(account_no);

	}

}
