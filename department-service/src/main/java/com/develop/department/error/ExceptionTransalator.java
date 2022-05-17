package com.develop.department.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.develop.department.entity.ErrorCode;

@RestControllerAdvice
public class ExceptionTransalator {

	@ExceptionHandler
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException noEle) {
		return new ResponseEntity<Object>(ErrorCode.NOT_FOUND.getMsg(), HttpStatus.NOT_FOUND);
	}

}
