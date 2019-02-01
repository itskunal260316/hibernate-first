package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name ="tbl_cust" 
//uniqueConstraints = {@UniqueConstraint (columnNames = { "cust_email" })}
)

public class Customer {
//we map this to the data base
	//no jdbc code.
	
	
	@Id //pk which is auto generated
	//embeddedId is used for a composite primary key
	@GeneratedValue //to generate an automatic seq
	@Column(name = "cust_id")
	private int id;
	
	//here we can define the properties
	
	@Column(name = "cust_name",columnDefinition="text")
	private String name;
	
	
	@Column(name = "cust_email", unique=true,columnDefinition="text")
	private String email;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
