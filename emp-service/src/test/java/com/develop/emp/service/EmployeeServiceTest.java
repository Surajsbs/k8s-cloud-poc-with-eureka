package com.develop.emp.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.develop.emp.entity.EmployeeEntity;
import com.develop.emp.entity.ResponseVo;
import com.develop.emp.reponse.ServiceResponse;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService service;

	@Test
	public void test_CreateUser() {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setEmail("Suraj@gmail.com");
		entity.setFirstName("Suraj");
		entity.setLastName("Savarat");
		List<EmployeeEntity> list = Arrays.asList(entity);
		ServiceResponse resp = service.save(list);
		Assert.assertNotNull(resp.getAssociations());
	}

	@Test
	public void test_FindEmp() {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setEmail("Suraj@gmail.com");
		entity.setFirstName("Suraj");
		entity.setLastName("Savarat");
		ResponseVo resp = service.findEmp(Long.valueOf(1));
		Assert.assertNotNull(resp.getEmp());
	}

}
