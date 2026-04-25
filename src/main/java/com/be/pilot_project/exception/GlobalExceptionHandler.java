package com.be.pilot_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ApiErrorResponse> handleAppException(AppException ex) {
		return build(ex.getStatus(), ex.getCode(), ex.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		return build(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getAllErrors().isEmpty()
			? "Dữ liệu không hợp lệ"
			: ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleUnknown(Exception ex) {
		return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", ex.getMessage());
	}
	
	private ResponseEntity<ApiErrorResponse> build(HttpStatus status, String code, String message) {
		ApiErrorResponse response = new ApiErrorResponse(
			status.value(),
			status.getReasonPhrase(),
			code,
			message,
			LocalDateTime.now()
		);
		return ResponseEntity.status(status).body(response);
	}
}
