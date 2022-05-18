package com.develop.emp.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.develop.emp.entity.Department;
import com.develop.emp.entity.EmployeeEntity;
import com.develop.emp.entity.ResponseVo;
import com.develop.emp.reponse.Association;
import com.develop.emp.reponse.ServiceResponse;
import com.develop.emp.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private EmployeeService service;

	@Test
	public void when_SaveSuccess() throws JsonProcessingException, Exception {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setEmail("Suraj@gmail.com");
		entity.setFirstName("Suraj");
		entity.setLastName("Savarat");
		List<EmployeeEntity> list = Arrays.asList(entity);

		Association asso = new Association();
		asso.setDeptId(Long.valueOf(1));
		asso.setUserId(Long.valueOf(1));
		ServiceResponse res = new ServiceResponse(Arrays.asList(asso));

		Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(res);

		mockMvc.perform(post("/api/emp/save").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(list))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.associations[0].employee_id", is(1)))
				.andExpect(jsonPath("$.associations[0].department_id", is(1)));

	}

	@Test
	public void when_SaveFailedButRequestSuccses() throws JsonProcessingException, Exception {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setEmail("Suraj@gmail.com");
		entity.setFirstName("Suraj");
		entity.setLastName("Savarat");
		List<EmployeeEntity> list = Arrays.asList(entity);

		ServiceResponse res = new ServiceResponse(Arrays.asList());

		Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(res);

		mockMvc.perform(post("/api/emp/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(list))
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void test_FindEmpWithId() throws JsonProcessingException, Exception {
		ResponseVo res = new ResponseVo();
		EmployeeEntity emp = new EmployeeEntity();
		emp.setDeptId(Long.valueOf(1));
		emp.setEmail("abc@example.com");
		emp.setFirstName("John");
		emp.setLastName("Smith");
		emp.setEmpId(Long.valueOf(1));
		Department dept = new Department();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		res.setDept(dept);
		res.setEmp(emp);
		Mockito.when(service.findEmp(ArgumentMatchers.any())).thenReturn(res);

		mockMvc.perform(get("/api/emp/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user.employee_id", is(1)))
				.andExpect(jsonPath("$.user.first_name", is("John")))
				.andExpect(jsonPath("$.user.email_id", is("abc@example.com")))
				.andExpect(jsonPath("$.dept.department_id", is(1)))
				.andExpect(jsonPath("$.dept.department_name", is("IT")))
				.andExpect(jsonPath("$.dept.department_code", is("204")));

	}
	
	@Test
	public void test_FetchAllEmps() throws JsonProcessingException, Exception {
		EmployeeEntity emp = new EmployeeEntity();
		emp.setDeptId(Long.valueOf(1));
		emp.setEmail("abc@example.com");
		emp.setFirstName("John");
		emp.setLastName("Smith");
		emp.setEmpId(Long.valueOf(1));
		
		Mockito.when(service.fetchAll()).thenReturn(Arrays.asList(emp));

		mockMvc.perform(get("/api/emp/fetchAll")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].employee_id", is(1)))
				.andExpect(jsonPath("$.[0].first_name", is("John")))
				.andExpect(jsonPath("$.[0].email_id", is("abc@example.com")));

	}

	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
