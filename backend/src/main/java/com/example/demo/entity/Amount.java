package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="amount")
public class Amount {
	@Id
	private String animal;
	private int price;
	
	@OneToMany(mappedBy="amount")
	private List<Customer> customer;
	
	public Amount(String animal, int price) {
		this.animal = animal;
		this.price = price;
	}
	public Amount() {
		
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Amount [animal=" + animal + ", price=" + price + "]";
	}
	
}
