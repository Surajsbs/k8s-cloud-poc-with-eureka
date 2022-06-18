package com.ust.employee.error;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8274703873438418074L;

	private String msg;
	private HttpStatus code;

	public ServiceException(String msg, HttpStatus code) {
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

}
