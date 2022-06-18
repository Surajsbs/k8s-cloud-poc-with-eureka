package com.ust.employee.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTransalator {

	@ExceptionHandler
	public ResponseEntity<Object> handleServiceException(ServiceException service) {
		return new ResponseEntity<Object>(service.getMsg(), service.getCode());
	}

}
