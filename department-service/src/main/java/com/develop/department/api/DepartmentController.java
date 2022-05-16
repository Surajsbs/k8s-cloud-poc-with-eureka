package com.develop.department.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.department.entity.DepartmentEntity;
import com.develop.department.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService service;

	@PostMapping(value = "/save")
	public DepartmentEntity save(@RequestBody DepartmentEntity request) {

		return service.save(request);
	}

	@GetMapping(value = "/{id}")
	public DepartmentEntity find(@PathVariable(name = "id") Long id) {
		return service.find(id);
	}

}
