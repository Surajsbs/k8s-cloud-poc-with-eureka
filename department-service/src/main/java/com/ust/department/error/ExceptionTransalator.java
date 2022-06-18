package com.ust.department.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ust.department.entity.ErrorCode;
import com.ust.department.response.ServiceResponse;

@RestControllerAdvice
public class ExceptionTransalator {

	@ExceptionHandler
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException noEle) {
		ServiceResponse resp = new ServiceResponse();
		resp.setResponse(ErrorCode.NOT_FOUND.getMsg());
		return new ResponseEntity<Object>(resp, HttpStatus.NOT_FOUND);
	}

}
