package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="dailyrecords")
public class DailyRecords {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int r_id;
	@Column(name="cust_id")
	private int custId;
	@JsonFormat(pattern = "yyyy-mm-dd HH:MM.SS",shape= JsonFormat.Shape.STRING)
	private String date;
	private int qty;
	private int price;
	
	@ManyToOne(targetEntity = Customer.class,fetch = FetchType.EAGER)
	@JoinColumn(name="cust_id",insertable = false,updatable = false)
	private Customer customer;
	
	public DailyRecords(int custId,String date, int qty, int price) {
		this.custId = custId;
		this.date=date;
		this.qty = qty;
		this.price = price;
	}
	public DailyRecords(int r_id,int custId, String date, int qty, int price) {
		this(custId,date, qty, price);
		this.r_id=r_id;
	}
	public DailyRecords() {
		
	}
	public void setR_id(int r_id) {
		this.r_id=r_id;
	}
	public int getR_id() {
		return r_id;
	}
	public int getcustId() {
		return custId;
	}
	public void setcustId(int custId) {
		this.custId = custId;
	}
	public String getDate() {
		return date;
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
	public String toString() {
		return "DailyRecords [r_id="+r_id+", custId=" + custId + ", date=" + date + ", qty=" + qty + ", price=" + price + "]";
	}
}
