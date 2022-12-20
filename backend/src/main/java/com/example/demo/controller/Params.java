package com.example.demo.controller;

public class Params {
	private String date;
	private int cust_id;
	public Params(String date, int cust_id) {
		this.date = date;
		this.cust_id = cust_id;
	}
	public Params() {
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	
}
