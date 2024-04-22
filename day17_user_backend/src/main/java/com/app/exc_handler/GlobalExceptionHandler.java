package com.app.exc_handler;

import java.time.LocalDateTime;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dto.ErrorResponse;

@ControllerAdvice // mandatory : to tell SC following class contains centralized exc handler
					// method/s
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
		System.out.println("in handle run time exc " + e);
		ErrorResponse resp = new ErrorResponse("Adding User failed!!!!!", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}

//How to tell SC : follo. method acts as the exc handling method
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<?> handleEmptyDataExcetpion(EmptyResultDataAccessException e) {
		System.out.println("in handle  exc " + e);
		ErrorResponse resp = new ErrorResponse(e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
	}

	@ExceptionHandler(UserHandlingException.class)
	public ResponseEntity<?> handleUserHandlingException(UserHandlingException e) {
		System.out.println("in handle user exc " + e);
		ErrorResponse resp = new ErrorResponse(e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("in handle invalid meth args ");
	//	System.out.println(ex.getBindingResult().getFieldErrors());
		StringBuilder sb = new StringBuilder("Validation Errors : ");
		ex.getBindingResult().getFieldErrors().forEach(e -> sb.append(e.getDefaultMessage()+" "));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(sb.toString(), LocalDateTime.now()));
	}

}
