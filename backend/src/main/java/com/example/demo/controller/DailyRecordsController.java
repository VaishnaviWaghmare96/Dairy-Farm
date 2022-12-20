package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.entity.DailyRecords;
import com.example.demo.service.RecordService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("record")
public class DailyRecordsController {
	private CustController custController;
	private RecordService recordserv;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Autowired
	public DailyRecordsController(RecordService recordserv, CustController custController) {
		this.recordserv = recordserv;
		this.custController = custController;
	}

	@GetMapping("/records")
	public List<Records> listAll() {
		List<DailyRecords> records = recordserv.fetchAll();
		return custController.getRecords(records);
	}

	@GetMapping("/monthrecords/{date}")
	public List<Records> monthlyRecord(@PathVariable Date date) {
		List<Records> records = listAll();
		List<Records> mrecords = new LinkedList<Records>();
		String[] currdate = (date + "").split("-");
		for (Records r : records) {
			String[] sdate = r.getDate().split("-");
			if ((Integer.parseInt(currdate[1]) - Integer.parseInt(sdate[1])) == 0
					&& (Integer.parseInt(currdate[0]) - Integer.parseInt(sdate[0])) == 0)
				mrecords.add(r);
		}
		return mrecords;
	}

	@GetMapping("/admindayrecords/{date}")
	public List<Records> adminDayRecord(@PathVariable String date) {
		List<Records> records = listAll();
		List<Records> mrecords = new LinkedList<Records>();
		String[] currdate = (date + "").split("-");
		for (Records r : records) {
			String[] sdate = r.getDate().split("-");
			if ((Integer.parseInt(currdate[2]) - Integer.parseInt(sdate[2].substring(0, 2))) == 0
					&& (Integer.parseInt(currdate[1]) - Integer.parseInt(sdate[1])) == 0
					&& (Integer.parseInt(currdate[0]) - Integer.parseInt(sdate[0])) == 0)
				mrecords.add(r);
		}
		return mrecords;
	}

	@PutMapping("/custmonthrecords")
	public List<Records> custMonthlyRecord(@RequestBody Params p) {
		List<Records> records = getByCust_Id(p.getCust_id());
		List<Records> mrecords = new LinkedList<Records>();
		String[] currdate = (p.getDate() + "").split("-");
		for (Records r : records) {
			String[] sdate = r.getDate().split("-");
			if ((Integer.parseInt(currdate[1]) - Integer.parseInt(sdate[1])) == 0
					&& (Integer.parseInt(currdate[0]) - Integer.parseInt(sdate[0])) == 0)
				mrecords.add(r);
		}
		return mrecords;
	}
	@GetMapping("/delete/{id}")
	public List<Records> deleteRecord(@PathVariable int r_id) {
		recordserv.delete(r_id);
		return listAll();
	}

	@GetMapping("adminDayRecords")
	public List<Records> getAllDayRecords() {
		String date = dtf.format(LocalDateTime.now());
		System.out.println(date);
		List<Records> records = listAll();
		List<Records> mrecords = new LinkedList<Records>();
		String[] currdate = (date).split("-");
		for (Records r : records) {
			String[] sdate = r.getDate().split("-");
			if ((Integer.parseInt(currdate[2].substring(0, 2)) - Integer.parseInt(sdate[2].substring(0, 2))) == 0
					&& (Integer.parseInt(currdate[1]) - Integer.parseInt(sdate[1])) == 0
					&& (Integer.parseInt(currdate[0]) - Integer.parseInt(sdate[0])) == 0)
				mrecords.add(r);
		}System.out.println(mrecords);
		return mrecords;
	}

	@GetMapping("/custrecord/{cust_id}")
	public List<Records> getByCust_Id(@PathVariable int cust_id) {
		System.out.println(cust_id);
		List<DailyRecords> records = recordserv.fetchByCust_Id(cust_id);
		System.out.println(custController.getRecords(records));
		return custController.getRecords(records);
	}

	@GetMapping("/record/{r_id}")
	public DailyRecords getById(@PathVariable int r_id) {
		return recordserv.fetchById(r_id);
	}

	@PostMapping("/save")
	public void saveRecord(@RequestBody Records record) {
		System.out.println(record);
		record.setDate(dtf.format(LocalDateTime.now()));
		DailyRecords drecord = new DailyRecords(record.getR_id(), record.getCust_id(), record.getDate(),
				record.getQty(), record.getPrice());
		System.out.println(drecord);
		recordserv.insertOrUpdate(drecord);
	}

	@PutMapping("/update")
	public List<Records> updateRecord(@RequestBody Records record) {
		DailyRecords drecord = new DailyRecords(record.getR_id(), record.getCust_id(), record.getDate(),
				record.getQty(), record.getPrice());
		recordserv.insertOrUpdate(drecord);
		return listAll();
	}
	@PutMapping("/custdayrecords")
	public List<Records> custDayRecord(@RequestBody Params p) {
		System.out.println(p);
		List<Records> records = getByCust_Id(p.getCust_id());
		List<Records> mrecords = new LinkedList<Records>();
		String[] currdate = (p.getDate() + "").split("-");
		for (Records r : records) {
			String[] sdate = r.getDate().split("-");
			if ((Integer.parseInt(currdate[2].substring(0, 2)) - Integer.parseInt(sdate[2].substring(0, 2))) == 0
					&& (Integer.parseInt(currdate[1]) - Integer.parseInt(sdate[1])) == 0
					&& (Integer.parseInt(currdate[0]) - Integer.parseInt(sdate[0])) == 0)
				mrecords.add(r);
		}
		return mrecords;
	}
	
}
