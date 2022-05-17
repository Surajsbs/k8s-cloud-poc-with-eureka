package com.develop.emp.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.develop.emp.entity.Department;
import com.develop.emp.entity.EmployeeEntity;
import com.develop.emp.entity.ResponseVo;
import com.develop.emp.mapper.ResponseMapper;
import com.develop.emp.reponse.ServiceResponse;
import com.develop.emp.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	private RestTemplate template;

	@Value("${dept-service.url}")
	String deptServiceUrl;

	@Value("${dept-service.port}")
	String deptServicePort;

	@Autowired
	public EmployeeService(RestTemplate template) {
		this.template = template;
	}

	public ServiceResponse save(List<EmployeeEntity> emps) {
		String url = deptServiceUrl + ":" + deptServicePort + "/api/dept/check/";
		List<EmployeeEntity> list = emps.stream()
				.filter(user -> template.getForObject(url + user.getDeptId(), Boolean.class))
				.collect(Collectors.toList());
		emps = repo.saveAll(list);
		return new ServiceResponse(ResponseMapper.INSTANCE.map(emps));
	}

	public ResponseVo findEmp(Long id) {
		ResponseVo response = new ResponseVo();
		EmployeeEntity user = repo.findByUserId(id);
		if (Objects.nonNull(user)) {
			String url = String.format(deptServiceUrl + ":" + deptServicePort + "/api/dept/%s", user.getDeptId());
			Department dept = template.getForObject(url, Department.class);
			response.setDept(dept);
			response.setUser(user);
			return response;
		} else {
			throw new com.develop.emp.error.ServiceException("No employee found for given id, " + id,
					HttpStatus.NOT_FOUND);
		}
	}

	public List<EmployeeEntity> fetchAll() {
		return repo.findAll();

	}

}
