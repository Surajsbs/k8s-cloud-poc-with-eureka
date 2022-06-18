package com.ust.employee.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.ust.employee.bean.EmployeeBean;
import com.ust.employee.entity.EmployeeEntity;
import com.ust.employee.reponse.ServiceResponse;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService service;

	@Test
	public void test_CreateUser() {
		EmployeeBean bean = new EmployeeBean();
		bean.setDeptId(Long.valueOf(1));
		bean.setEmail("Suraj@gmail.com");
		bean.setFirstName("Suraj");
		bean.setLastName("Savarat");
		List<EmployeeBean> list = Arrays.asList(bean);
		ServiceResponse resp = service.save(list);
		assertNotNull(resp.getResponse());
	}

	@Test
	public void test_FindEmp() {
		EmployeeEntity bean = new EmployeeEntity();
		bean.setDeptId(Long.valueOf(1));
		bean.setEmail("Suraj@gmail.com");
		bean.setFirstName("Suraj");
		bean.setLastName("Savarat");
		service.findEmp(Long.valueOf(1));
//		assertNotNull(resp.getEmp());
	}

}
