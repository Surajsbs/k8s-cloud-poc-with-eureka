package com.ust.department.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ust.department.bean.DepartmentBean;
import com.ust.department.entity.DepartmentEntity;

@Mapper
public interface ServiceMapper {
	ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

	DepartmentEntity transaformToEntity(DepartmentBean request);

	List<DepartmentBean> transaformToBeans(List<DepartmentEntity> findAll);

	DepartmentBean transaformToBean(DepartmentEntity findByDeptId);

}
