package com.ust.employee.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentDTO {
	@JsonProperty("department_id")
	private Long deptId;

	@JsonProperty("department_name")
	private String deptName;

	@JsonProperty("department_address")
	private String deptAddress;

	@JsonProperty("department_code")
	private String deptCode;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}
