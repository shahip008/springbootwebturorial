package com.ps.springbootwebturorial.advices;

import org.springframework.http.HttpStatus;

//@Data
public class ApiError {
	private HttpStatus status;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public ApiError() {
		
	}
	public ApiError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private String message;
}
