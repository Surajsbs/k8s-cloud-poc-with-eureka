package com.ust.employee.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ust.employee.bean.EmployeeBean;
import com.ust.employee.entity.Department;
import com.ust.employee.entity.DepartmentDTO;
import com.ust.employee.entity.EmployeeEntity;
import com.ust.employee.reponse.Association;
import com.ust.employee.reponse.EmployeeDTO;

@Mapper
public interface ServiceMapper {
	ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

	@Mapping(target = "empId", source = "empId")
	@Mapping(target = "deptId", source = "deptId")
	List<Association> map(List<EmployeeEntity> users);

	List<EmployeeEntity> transformToEntity(List<EmployeeBean> emps);

	List<EmployeeBean> transformToBean(List<EmployeeEntity> emps);

	EmployeeBean transformToBean(EmployeeEntity emps);

	@Mapping(source = "deptId", target = "deptId")
	@Mapping(source = "deptName", target = "deptName")
	@Mapping(source = "deptAddress", target = "deptAddress")
	@Mapping(source = "deptCode", target = "deptCode")
	DepartmentDTO transformDeptBean(Department forObject);

	EmployeeDTO transformDeptBean(EmployeeBean forObject);

}
