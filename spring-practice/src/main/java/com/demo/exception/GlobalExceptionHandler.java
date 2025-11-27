package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	ResponseEntity<ApplicationException> handleException(ApplicationException ex) {
		ApplicationException e = new ApplicationException(ex.getMessage());
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}

}
