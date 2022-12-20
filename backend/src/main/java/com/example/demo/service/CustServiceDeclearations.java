package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Customer;


public interface CustServiceDeclearations {
	public List<Customer> fetchAll();
	public Customer fetchById(int cust_id);
	public void insertOrUpdate(Customer customer);
//	public void delete(int cust_id);
}
