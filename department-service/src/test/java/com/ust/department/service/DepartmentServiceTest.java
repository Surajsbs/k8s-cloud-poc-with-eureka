package com.ust.department.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ust.department.bean.DepartmentBean;
import com.ust.department.entity.DepartmentEntity;
import com.ust.department.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
	@Mock
	private UserRepository repo;

	@InjectMocks
	private DepartmentService service;

	@Test
	public void testSave() {
		DepartmentBean request = new DepartmentBean();
		request.setDeptId(Long.valueOf(1));
		request.setDeptCode("204");
		request.setDeptName("IT");
		request.setDeptAddress("204/050,B, Hosur Road, Mumbai");

		DepartmentEntity entity = new DepartmentEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setDeptCode("204");
		entity.setDeptName("IT");
		entity.setDeptAddress("204/050,B, Hosur Road, Mumbai");

		when(repo.save(Mockito.any())).thenReturn(entity);
		service.save(request);
	}

	@Test
	public void testFindDept() {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setDeptCode("204");
		entity.setDeptName("IT");
		entity.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		when(repo.findByDeptId(Mockito.anyLong())).thenReturn(Optional.of(entity));
		DepartmentBean bean = service.find(1L);
		assertEquals("204", bean.getDeptCode());
		assertEquals("IT", bean.getDeptName());
	}

	@Test
	public void testDeptNotAvailable() {
		when(repo.findByDeptId(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		DepartmentBean bean = service.find(1L);
		assertEquals(null, bean);
	}

	@Test
	public void testCheckNoDept() {
		when(repo.findByDeptId(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		boolean isPresent = service.check(1L);
		assertFalse(isPresent);
	}

	@Test
	public void testCheckValidDept() {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setDeptCode("204");
		entity.setDeptName("IT");
		entity.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		when(repo.findByDeptId(Mockito.anyLong())).thenReturn(Optional.of(entity));
		boolean isPresent = service.check(1L);
		assertTrue(isPresent);
	}

	@Test
	public void testFetchAllDept() {
		DepartmentEntity entity = new DepartmentEntity();
		entity.setDeptId(Long.valueOf(1));
		entity.setDeptCode("204");
		entity.setDeptName("IT");
		entity.setDeptAddress("204/050,B, Hosur Road, Mumbai");
		when(repo.findAll()).thenReturn(Arrays.asList(entity));
		List<DepartmentBean> beans = service.fetchAll();
		assertEquals(1, beans.size());
		assertEquals("204", beans.get(0).getDeptCode());
	}

}
