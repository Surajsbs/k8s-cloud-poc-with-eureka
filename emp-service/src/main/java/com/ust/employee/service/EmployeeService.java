package com.ust.employee.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ust.employee.bean.EmployeeBean;
import com.ust.employee.entity.Department;
import com.ust.employee.entity.DepartmentDTO;
import com.ust.employee.entity.EmployeeEntity;
import com.ust.employee.mapper.ServiceMapper;
import com.ust.employee.reponse.DepartmentSaveResponse;
import com.ust.employee.reponse.EmployeeDTO;
import com.ust.employee.reponse.ServiceResponse;
import com.ust.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	private RestTemplate template;

	@Value("${dept-service.check-url}")
	String checkSaveUrl;

	@Value("${dept-service.fetch-url}")
	String deptFetchUrl;

	@Autowired
	public EmployeeService(RestTemplate template) {
		this.template = template;
	}

	public ServiceResponse save(List<EmployeeBean> emps) {
		List<EmployeeEntity> entities = ServiceMapper.INSTANCE.transformToEntity(emps);
		String url = checkSaveUrl + "/";
		List<EmployeeEntity> list = entities.stream().filter(
				emp -> template.getForObject(url + emp.getDeptId(), DepartmentSaveResponse.class).isResponse())
				.collect(Collectors.toList());
		entities = repo.saveAll(list);
		if (emps != null && !emps.isEmpty()) {
			return new ServiceResponse(ServiceMapper.INSTANCE.map(entities));
		}
		return new ServiceResponse(null);
	}

	public ServiceResponse findEmp(Long id) {
		EmployeeDTO dto = new EmployeeDTO();
		EmployeeBean emp = ServiceMapper.INSTANCE.transformToBean(repo.findByEmpId(id));
		if (Objects.nonNull(emp)) {
			String url = String.format(deptFetchUrl + "/%s", emp.getDeptId());
			Department dept = template.getForObject(url, Department.class);
			DepartmentDTO deptDto = ServiceMapper.INSTANCE.transformDeptBean(dept);
			dto.setDept(deptDto);
			dto.setEmail(emp.getEmail());
			dto.setEmpId(emp.getEmpId());
			dto.setFirstName(emp.getFirstName());
			dto.setLastName(emp.getLastName());
			return new ServiceResponse(dto);
		} else {
			throw new com.ust.employee.error.ServiceException("No employee found for given id, " + id,
					HttpStatus.NOT_FOUND);
		}
	}

	public List<EmployeeBean> fetchAll() {
		return ServiceMapper.INSTANCE.transformToBean(repo.findAll());
	}

}
