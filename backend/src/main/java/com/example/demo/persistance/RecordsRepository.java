package com.example.demo.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DailyRecords;


public interface RecordsRepository extends JpaRepository<DailyRecords, Integer> {
	public List<DailyRecords> findAllByCustId(int cust_id);
}
