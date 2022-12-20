package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.persistance.CustRepositor;

@Service
public class CustService implements CustServiceDeclearations{
	private CustRepositor custRepo;
	@Autowired
	public CustService(CustRepositor custRepo) {
		this.custRepo = custRepo;
	}
	public List<Customer> fetchAll() {
		return custRepo.findAll();
	}
	public List<Customer> fetchAllById(int cust_id) {
		List<Customer> list = new ArrayList<Customer>();
		Customer cust= custRepo.findById(cust_id).get();
		list.add(cust);
		return list;
	}
	public void insertOrUpdate(Customer customer) {
		custRepo.save(customer);
	}
	public void delete(int cust_id) {
		custRepo.deleteById(cust_id);
	}
	
	public Customer fetchById(int cust_id) {
		return custRepo.findById(cust_id).get();
	}
}
