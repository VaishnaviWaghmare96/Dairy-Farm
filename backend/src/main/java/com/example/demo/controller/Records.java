package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Records {
	private int r_id;
	private int cust_id;
	private String name;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm.SS",shape= JsonFormat.Shape.STRING)
	private String date = dtf.format(LocalDateTime.now());
	private String animal;
	private int qty;
	private int price;
	public Records() {
	}
	public Records(int cust_id,String animal,String name,int price) {
		this.cust_id = cust_id;
		this.animal = animal;
		this.name = name;
		this.price = price;
	}
	public Records(int cust_id,int qty, int price) {
		this.cust_id = cust_id;
		this.qty = qty;
		this.price = price;
	}
	public Records(int r_id, int cust_id, String name, String date,String animal,int qty, int price) {
		this(cust_id,qty,price);
		this.r_id = r_id;
		this.name = name;
		this.date = date;
		this.animal=animal;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
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
	public String getDate() {
		return this.date;
	}
	public String getAnimal() {
		return this.animal;
	}
	public void setAnimal(String animal) {
		this.animal=animal;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Records [r_id=" + r_id + ", cust_id=" + cust_id + ", name=" + name + ", date=" + date + ", qty=" + qty
				+ ", price=" + price + "]";
	}
}
