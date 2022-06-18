package com.ust.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ust.department.bean.DepartmentBean;
import com.ust.department.entity.ErrorCode;
import com.ust.department.response.ServiceResponse;
import com.ust.department.service.DepartmentService;

@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

	@Autowired
	private DepartmentService service;

	@PostMapping(value = "/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void save(@RequestBody DepartmentBean request) {
		service.save(request);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
		DepartmentBean resp = service.find(id);
		if (resp == null) {
			return new ResponseEntity<>(ErrorCode.NOT_FOUND.getMsg(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping(value = "/fetchAll")
	public ResponseEntity<ServiceResponse> fetchAll() {
		List<DepartmentBean> response = service.fetchAll();
		ServiceResponse sResp = new ServiceResponse();
		if (response.isEmpty()) {
			sResp.setResponse(ErrorCode.NOT_FOUND.getMsg());
			return new ResponseEntity<>(sResp, HttpStatus.NOT_FOUND);
		}
		sResp.setResponse(response);
		return new ResponseEntity<>(sResp, HttpStatus.OK);

	}

	@GetMapping(value = "/check/{id}")
	public ResponseEntity<ServiceResponse> check(@PathVariable(name = "id") Long id) {
		boolean isPresent = service.check(id);
		ServiceResponse sResp = new ServiceResponse();
		if (isPresent) {
			sResp.setResponse(isPresent);
			return new ResponseEntity<>(sResp, HttpStatus.OK);
		}
		sResp.setResponse(false);
		return new ResponseEntity<>(sResp, HttpStatus.OK);
	}

}
