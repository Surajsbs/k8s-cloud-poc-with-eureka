package com.ust.employee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.employee.bean.EmployeeBean;
import com.ust.employee.entity.DepartmentDTO;
import com.ust.employee.entity.EmployeeEntity;
import com.ust.employee.reponse.Association;
import com.ust.employee.reponse.EmployeeDTO;
import com.ust.employee.reponse.ServiceResponse;
import com.ust.employee.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private EmployeeController controller;
	@Mock
	private EmployeeService service;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void when_SaveSuccess() throws JsonProcessingException, Exception {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setEmail("John.Smith@gmail.com");
		entity.setFirstName("John");
		entity.setLastName("Smith");
		List<EmployeeEntity> list = Arrays.asList(entity);

		Association asso = new Association();
		asso.setDeptId(Long.valueOf(1));
		asso.setEmpId(Long.valueOf(1));
		ServiceResponse res = new ServiceResponse(Arrays.asList(asso));

		Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(res);

		mockMvc.perform(post("/api/emp/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(list))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.associations[0].employee_id", is(1)))
				.andExpect(jsonPath("$.associations[0].department_id", is(1)));

	}

	@Test
	public void when_SaveFailedButRequestSuccses() throws JsonProcessingException, Exception {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setEmail("John.Smith@gmail.com");
		entity.setFirstName("John");
		entity.setLastName("Smith");
		List<EmployeeEntity> list = Arrays.asList(entity);

		ServiceResponse res = new ServiceResponse(Arrays.asList());

		Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(res);

		mockMvc.perform(post("/api/emp/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(list))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

	}

	@Test
	public void test_FindEmpWithId() throws JsonProcessingException, Exception {

		EmployeeDTO empDto = new EmployeeDTO();
		empDto.setEmail("John.Smith@gmail.com");
		empDto.setFirstName("John");
		empDto.setLastName("Smith");
		empDto.setEmpId(Long.valueOf(1));
		DepartmentDTO dept = new DepartmentDTO();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		empDto.setDept(dept);
		Mockito.when(service.findEmp(ArgumentMatchers.any())).thenReturn(new ServiceResponse(empDto));

		mockMvc.perform(get("/api/emp/{id}", 1L).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.user.employee_id", is(1))).andExpect(jsonPath("$.user.first_name", is("John")))
				.andExpect(jsonPath("$.user.email_id", is("abc@example.com")))
				.andExpect(jsonPath("$.dept.department_id", is(1)))
				.andExpect(jsonPath("$.dept.department_name", is("IT")))
				.andExpect(jsonPath("$.dept.department_code", is("204")));

	}

	@Test
	public void test_FetchAllEmps() throws JsonProcessingException, Exception {
		EmployeeBean emp = new EmployeeBean();
		emp.setDeptId(Long.valueOf(1));
		emp.setEmail("John.Smith@gmail.com");
		emp.setFirstName("John");
		emp.setLastName("Smith");
		emp.setEmpId(Long.valueOf(1));

		Mockito.when(service.fetchAll()).thenReturn(Arrays.asList(emp));

		mockMvc.perform(get("/api/emp/fetchAll").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].employee_id", is(1))).andExpect(jsonPath("$.[0].first_name", is("John")))
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
