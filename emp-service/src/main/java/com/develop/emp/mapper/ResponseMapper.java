package com.develop.emp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.develop.emp.entity.EmployeeEntity;
import com.develop.emp.reponse.Association;

@Mapper
public interface ResponseMapper {
	ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

	@Mapping(target = "userId", source = "empId")
	@Mapping(target = "department_id", source = "department_id")
	List<Association> map(List<EmployeeEntity> users);

}
