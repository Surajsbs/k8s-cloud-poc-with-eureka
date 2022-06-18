package com.ust.department.error;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -8274703873438418074L;

	private String msg;
	private String code;

	public ServiceException(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
