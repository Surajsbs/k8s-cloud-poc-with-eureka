package com.ust.employee.reponse;

public class ServiceResponse {
	private Object response;

	public ServiceResponse(Object response) {
		this.response = response;
	}

	public ServiceResponse() {
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
