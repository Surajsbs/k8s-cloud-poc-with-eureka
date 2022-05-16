package com.develop.department.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.department.entity.DepartmentEntity;
import com.develop.department.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

	@Autowired
	private UserRepository repo;

	public DepartmentEntity save(DepartmentEntity request) {
		return repo.save(request);
	}

	public DepartmentEntity find(Long id) {
		Optional<DepartmentEntity> dept = repo.findByDeptId(id);
		return dept.get();
	}

}
