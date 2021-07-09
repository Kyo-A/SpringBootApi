package com.example.demo.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.response.ExceptionResponse;;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException e, WebRequest request) {
	    ExceptionResponse error = new ExceptionResponse(new Date(), e.getMessage(),
	            request.getDescription(false),HttpStatus.NOT_FOUND.getReasonPhrase());
	    return new ResponseEntity<ExceptionResponse>(error, HttpStatus.NOT_FOUND);
	}

}
