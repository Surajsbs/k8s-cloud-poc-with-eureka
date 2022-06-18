package com.ust.department.entity;

public enum ErrorCode {
	NOT_FOUND("No department(s) are found for given id");

	String msg;

	ErrorCode(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
