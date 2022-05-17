package com.develop.department.entity;

public enum ErrorCode {
	NOT_FOUND("No department found for given id");

	String msg;

	ErrorCode(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
