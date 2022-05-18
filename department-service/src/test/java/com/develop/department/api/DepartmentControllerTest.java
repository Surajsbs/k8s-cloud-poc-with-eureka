package com.develop.department.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import com.develop.department.entity.DepartmentEntity;
import com.develop.department.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private DepartmentService service;

	@Test
	public void when_SaveSuccess() throws JsonProcessingException, Exception {
		DepartmentEntity dept = new DepartmentEntity();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		
		Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(dept);

		mockMvc.perform(post("/api/dept/save").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(dept))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.department_code", is("204")))
				.andExpect(jsonPath("$.department_id", is(1)));

	}
	
	@Test
	public void when_FindDept() throws JsonProcessingException, Exception {
		DepartmentEntity dept = new DepartmentEntity();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		
		Mockito.when(service.find(ArgumentMatchers.any())).thenReturn(dept);

		mockMvc.perform(get("/api/dept/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(dept))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.department_code", is("204")))
				.andExpect(jsonPath("$.department_id", is(1)));

	}
	
	@Test
	public void when_FetchAllDeps() throws JsonProcessingException, Exception {
		DepartmentEntity dept = new DepartmentEntity();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		
		Mockito.when(service.fetchAll()).thenReturn(Arrays.asList(dept));

		mockMvc.perform(get("/api/dept/fetchAll").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(dept))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].department_code", is("204")))
				.andExpect(jsonPath("$.[0].department_id", is(1)));

	}
	
	@Test
	public void when_CheckDeptExist() throws JsonProcessingException, Exception {
		DepartmentEntity dept = new DepartmentEntity();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		
		Mockito.when(service.check(ArgumentMatchers.any())).thenReturn(true);

		mockMvc.perform(get("/api/dept/check/1").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(dept))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(true)));

	}
	
	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
}
