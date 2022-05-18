package com.develop.emp.api;

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

import com.develop.emp.entity.EmployeeEntity;
import com.develop.emp.entity.ResponseVo;
import com.develop.emp.reponse.ServiceResponse;
import com.develop.emp.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping(value = "/save")
	public ResponseEntity<ServiceResponse> saveEmp(@RequestBody List<EmployeeEntity> users) {
		ServiceResponse resp = service.save(users);
		if (resp.getAssociations().isEmpty()) {
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseVo findEmp(@PathVariable(name = "id") Long id) {
		return service.findEmp(id);
	}

	@GetMapping(value = "/fetchAll")
	public List<EmployeeEntity> fetchAll() {
		return service.fetchAll();
	}

}
