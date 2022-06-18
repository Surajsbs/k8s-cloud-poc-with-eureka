package com.ust.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.department.bean.DepartmentBean;
import com.ust.department.entity.DepartmentEntity;
import com.ust.department.mapper.ServiceMapper;
import com.ust.department.repository.UserRepository;

@Service
public class DepartmentService {

	@Autowired
	private UserRepository repo;

	public void save(DepartmentBean request) {
		DepartmentEntity entityRequest = ServiceMapper.INSTANCE.transaformToEntity(request);
		repo.save(entityRequest);
	}

	public DepartmentBean find(Long id) {
		Optional<DepartmentEntity> entity = repo.findByDeptId(id);
		if (entity.isPresent()) {
			return ServiceMapper.INSTANCE.transaformToBean(entity.get());
		}
		return null;
	}

	public boolean check(Long id) {
		if (find(id) == null) {
			return false;
		}
		return true;
	}

	public List<DepartmentBean> fetchAll() {
		return ServiceMapper.INSTANCE.transaformToBeans(repo.findAll());
	}

}
