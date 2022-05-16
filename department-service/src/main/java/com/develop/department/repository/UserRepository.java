package com.develop.department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.department.entity.DepartmentEntity;

@Repository
public interface UserRepository extends JpaRepository<DepartmentEntity, Long> {

	Optional<DepartmentEntity> findByDeptId(Long id);

}
