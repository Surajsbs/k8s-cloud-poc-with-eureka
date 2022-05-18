package com.develop.department.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.develop.department.entity.DepartmentEntity;
import com.develop.department.service.DepartmentService;

@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

	@Autowired
	private DepartmentService service;

	@PostMapping(value = "/save")
	@ResponseStatus(code = HttpStatus.OK)
	public DepartmentEntity save(@RequestBody DepartmentEntity request) {
		return service.save(request);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public DepartmentEntity find(@PathVariable(name = "id") Long id) {
		return service.find(id);
	}

	@GetMapping(value = "/fetchAll")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DepartmentEntity> fetchAll() {
		return service.fetchAll();
	}

	@GetMapping(value = "/check/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean check(@PathVariable(name = "id") Long id) {
		return service.check(id);
	}

}
