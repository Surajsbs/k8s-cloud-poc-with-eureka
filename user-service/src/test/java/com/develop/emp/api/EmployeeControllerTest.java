package com.develop.emp.api;

import static org.hamcrest.CoreMatchers.is;
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

import com.develop.emp.entity.EmployeeEntity;
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
	public void when() throws JsonProcessingException, Exception {
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
				.content(toJson(list)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.associations[0].employee_id", is(1)))
				.andExpect(jsonPath("$.associations[0].department_id", is(1)));


	}

	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
