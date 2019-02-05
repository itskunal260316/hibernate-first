package com.lti.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_account")

public class Account {

	@Id
	@GeneratedValue
	@Column(name = "account_no") // pk
	private int acno;

	@Column(name = "name")
	private String name;

	@Column(name = "account_type")
	private String type;

	@Column(name = "account_balance")
	private double balance;

	@OneToMany(mappedBy = "account")
	// we specify that the foreign key is in another class
	// describe the relationship field with the above annotation
	private Set<AccountTransaction> transactions;

	public int getAcno() {
		return acno;
	}

	public void setAcno(int acno) {
		this.acno = acno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
