package com.example.demo.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.entity.DailyRecords;
import com.example.demo.service.CustService;

@RestController
@RequestMapping("customer")
public class CustController {
	private CustService custserv;
	private AmountController amountCont;
	@Autowired
	public CustController(CustService custserv,AmountController amountCont) {
		this.custserv = custserv;
		this.amountCont = amountCont;
	}

	@GetMapping("/list")
	public List<Customer> listAll() {
		return custserv.fetchAll();
	}
	@GetMapping("/listById/{cust_id}")
	public List<Customer> getAllById(@PathVariable int cust_id) {
		return custserv.fetchAllById(cust_id);
	}
	@GetMapping("/list/{cust_id}")
	public Customer getById(@PathVariable int cust_id) {
		return custserv.fetchById(cust_id);
	}
	@PostMapping("/save")
	public List<Customer> saveEmployee(@RequestBody Customer customer)
	{	System.out.println(customer);
		customer.setCust_id(0);
		custserv.insertOrUpdate(customer);
		return listAll();
	}
	@PutMapping("/update")
	public Customer updateCust(@RequestBody Customer customer) {
		custserv.insertOrUpdate(customer);
		return customer;
	}

	public List<Records> getRecords(List<DailyRecords> drecords) {
		List<Records> records= new LinkedList<Records>();
		Records record = null;
		Customer customer=null;
		for (DailyRecords r : drecords) {
			customer=this.getById(r.getcustId());
			record = new Records(r.getR_id(),customer.getCust_id(),customer.getName(),r.getDate()+"",customer.getAnimal(),r.getQty(),r.getPrice());
			records.add(record);
		}
		return records;
	}
	
	@GetMapping("/todayRecords")
	public List<Records> getCustRecords(){
		List<Records> records= new LinkedList<Records>();
		Records record = null;
		List<Customer> customers= listAll();
		int price;
		for (Customer c : customers) {
			price = amountCont.getByAnimal(c.getAnimal()).getPrice();
			record = new Records(c.getCust_id(),c.getAnimal(),c.getName(),price);
			record.setR_id(0);
			records.add(record);
		}
		return records;
	}
//@GetMapping("/listAll")
//public String displayAll(Model model) {
//	List<Customer> customers=custserv.fetchAll();
//	model.addAttribute("Customers",customers);
//	return "display";
//}
@DeleteMapping("/delete/{id}")
public String deleteEmployee(@PathVariable int id)
{
	custserv.delete(id);
	return "display";
}
}
