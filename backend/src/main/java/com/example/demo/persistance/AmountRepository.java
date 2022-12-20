package com.example.demo.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Amount;

public interface AmountRepository extends JpaRepository<Amount, String> {

}
