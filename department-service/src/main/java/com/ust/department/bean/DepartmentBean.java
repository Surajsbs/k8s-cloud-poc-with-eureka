package com.ust.department.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentBean {
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

	public DepartmentBean(long deptId, String deptName, String deptAddress, String deptCode) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptAddress = deptAddress;
		this.deptCode = deptCode;
	}

	public DepartmentBean() {
	}

}
