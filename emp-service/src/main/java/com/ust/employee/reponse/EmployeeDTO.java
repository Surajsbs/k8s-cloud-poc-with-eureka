package com.ust.employee.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ust.employee.entity.DepartmentDTO;

public class EmployeeDTO {
	@JsonProperty("emp_id")
	private Long empId;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("email_id")
	private String email;

	@JsonProperty("dept")
	private DepartmentDTO dept;

	public DepartmentDTO getDept() {
		return dept;
	}

	public void setDept(DepartmentDTO dept) {
		this.dept = dept;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
