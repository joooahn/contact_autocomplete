package com.example.miniproject.model.exception;

import org.springframework.http.HttpStatus;

/**
 * 파라미터가 잘못되었을 때 발생하는 예외
 */
public class InvalidParameterException extends ContactException {

	private static final int status = HttpStatus.PRECONDITION_FAILED.value();

	public InvalidParameterException(String message) {
		super(status, message);
	}
}
