package com.ust.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.employee.bean.EmployeeBean;
import com.ust.employee.reponse.ServiceResponse;
import com.ust.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping(value = "/save")
	public ResponseEntity<ServiceResponse> saveEmp(@RequestBody List<EmployeeBean> users) {
		ServiceResponse resp = service.save(users);
		if (resp.getResponse() != null) {
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ServiceResponse> findEmp(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(service.findEmp(id), HttpStatus.OK);
	}

	@GetMapping(value = "/fetchAll")
	public ResponseEntity<ServiceResponse> fetchAll() {
		List<EmployeeBean> emps = service.fetchAll();
		if (emps != null && !emps.isEmpty()) {
			return new ResponseEntity<>(new ServiceResponse(emps), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ServiceResponse(), HttpStatus.OK);
	}

}
