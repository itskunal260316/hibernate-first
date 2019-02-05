package com.lti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Columns;

@Entity
@Table(name = "tbl_account_transaction")

public class AccountTransaction {

	@Id
	@GeneratedValue
	@Column(name = "transaction_no")
	private int txno;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAndTime;

	private double amount;// if not declared then the table attr name will be the same as the id name

	@Column(name = "transaction_type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "account_no") // fk
	private Account account;// this is the variable declared are the fk in the one to many annnotation

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getTxno() {
		return txno;
	}

	public void setTxno(int txno) {
		this.txno = txno;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
