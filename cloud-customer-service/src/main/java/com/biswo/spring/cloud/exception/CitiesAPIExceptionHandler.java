package com.biswo.spring.cloud.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.biswo.spring.cloud.entity.ResponseDTO;
@ControllerAdvice
public class CitiesAPIExceptionHandler extends ResponseEntityExceptionHandler {
	Logger LOG = LoggerFactory.getLogger(CitiesAPIExceptionHandler.class);
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		FieldError fieldError = ex.getBindingResult().getFieldError();
		ResponseDTO responseDTO = new ResponseDTO(status.toString(), fieldError.getDefaultMessage());

		return ResponseEntity.badRequest().body(responseDTO);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
		//LOG.error(ex.getMessage(), ex);

		ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());

		return ResponseEntity.badRequest().body(responseDTO);
	}
}
