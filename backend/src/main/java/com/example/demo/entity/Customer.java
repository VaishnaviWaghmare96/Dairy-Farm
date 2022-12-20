package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cust_id;
	private String name;
	@Column(name="phone_no")
	private String phoneNo;
	private String animal;
	private String password;
	
	@OneToMany(mappedBy="customer")
	private List<DailyRecords> dailyRecords;
	
	@ManyToOne(targetEntity = Amount.class,fetch = FetchType.EAGER)
	@JoinColumn(name="animal",insertable = false,updatable = false)
	private Amount amount;
	
	public Customer(int cust_id, String name, String phoneNo, String animal, String password) {
		this.cust_id = cust_id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.animal = animal;
		this.password = password;
	}
	public Customer() {
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", name=" + name + ", phoneNo=" + phoneNo + ", animal=" + animal
				+ ", password=" + password + "]";
	}
}
