package com.ust.department.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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
import com.ust.department.bean.DepartmentBean;
import com.ust.department.entity.DepartmentEntity;
import com.ust.department.entity.ErrorCode;
import com.ust.department.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private DepartmentController controller;
	@Mock
	private DepartmentService service;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testSaveSuccess() throws JsonProcessingException, Exception {
		DepartmentBean dept = new DepartmentBean();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");

		Mockito.doNothing().when(service).save(ArgumentMatchers.any());

		mockMvc.perform(post("/api/dept/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(dept))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	}

	@Test
	public void testFindDeptSuccess() throws JsonProcessingException, Exception {
		DepartmentBean dept = new DepartmentBean();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");

		Mockito.when(service.find(ArgumentMatchers.any())).thenReturn(dept);

		mockMvc.perform(get("/api/dept/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.department_code", is("204"))).andExpect(jsonPath("$.department_id", is(1)));
	}

	@Test
	public void testNoDeptFOund() throws JsonProcessingException, Exception {
		Mockito.when(service.find(ArgumentMatchers.any())).thenReturn(null);
		mockMvc.perform(get("/api/dept/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$", is(ErrorCode.NOT_FOUND.getMsg())));
	}

	@Test
	public void testFetchAllDeptsSuccess() throws JsonProcessingException, Exception {
		DepartmentBean dept = new DepartmentBean();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		Mockito.when(service.fetchAll()).thenReturn(Arrays.asList(dept));
		mockMvc.perform(get("/api/dept/fetchAll")).andExpect(status().isOk())
				.andExpect(jsonPath("$.response[0].department_code", is("204")))
				.andExpect(jsonPath("$.response[0].department_id", is(1)));
	}

	@Test
	public void testNotDeptsAvailable() throws JsonProcessingException, Exception {
		Mockito.when(service.fetchAll()).thenReturn(Arrays.asList());
		mockMvc.perform(get("/api/dept/fetchAll")).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.response", is(ErrorCode.NOT_FOUND.getMsg())));
	}

	@Test
	public void testCheckDeptExistSuccess() throws JsonProcessingException, Exception {
		DepartmentEntity dept = new DepartmentEntity();
		dept.setDeptId(Long.valueOf(1));
		dept.setDeptCode("204");
		dept.setDeptName("IT");
		dept.setDeptAddress("204/050,B, Hosur Road, Mumbai");

		Mockito.when(service.check(ArgumentMatchers.any())).thenReturn(true);
		mockMvc.perform(get("/api/dept/check/1").content(toJson(dept)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.response", is(true)));
	}

	@Test
	public void testCheckDeptExistFail() throws JsonProcessingException, Exception {
		Mockito.when(service.check(ArgumentMatchers.any())).thenReturn(false);
		mockMvc.perform(get("/api/dept/check/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.response", is(false)));
	}

	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
