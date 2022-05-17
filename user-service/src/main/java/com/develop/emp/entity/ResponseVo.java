package com.develop.emp.entity;

public class ResponseVo {
	private EmployeeEntity user;
	private Department dept;

	public EmployeeEntity getUser() {
		return user;
	}

	public void setUser(EmployeeEntity user) {
		this.user = user;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

}
