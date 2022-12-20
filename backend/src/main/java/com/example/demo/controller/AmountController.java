package com.example.demo.controller;

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

import com.example.demo.entity.Amount;
import com.example.demo.service.AmountService;

@RestController
@RequestMapping("amount")
public class AmountController {
	private AmountService amountserv;
	@Autowired
	public AmountController(AmountService amountserv) {
		this.amountserv = amountserv;
	}

	@GetMapping("/all")
	public List<Amount> listAll() {
		List<Amount> amount=amountserv.fetchAll();
		return amount;
	}
	@GetMapping("/byAnimal")
	public Amount getByAnimal(String animal) {
		Amount amount=amountserv.fetchById(animal);
		return amount;
	}
	
	@PostMapping("/save")
	public List<Amount> saveEmployee(@RequestBody Amount amount)
	{	System.out.println(amount);
		amountserv.insertOrUpdate(amount);
		return listAll();
	}
	@PutMapping("/update")
	public Amount update(@RequestBody Amount amount) {
		amountserv.insertOrUpdate(amount);
		return amount;
	}
	@DeleteMapping("/delete/{animal}")
	public String deleteEmployee(@PathVariable String animal)
	{
		amountserv.delete(animal);
		return "Deleted Successfully";
	}
}
