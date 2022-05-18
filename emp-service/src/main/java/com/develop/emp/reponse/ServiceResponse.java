package com.develop.emp.reponse;

import java.util.List;

public class ServiceResponse {
	private List<Association> associations;

	public List<Association> getAssociations() {
		return associations;
	}

	public ServiceResponse(List<Association> associations) {
		this.associations = associations;
	}

}
