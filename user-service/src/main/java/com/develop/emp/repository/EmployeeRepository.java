package com.develop.emp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.emp.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	Optional<EmployeeEntity> findByDeptId(Long id);

	EmployeeEntity findByUserId(Long id);

}
