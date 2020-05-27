package com.example.miniproject.model.exception;

import org.springframework.http.HttpStatus;

/**
 * 데이터를 찾지 못 했을 때 발생하는 예외
 */
public class NotFoundException extends ContactException {

	private static final int status = HttpStatus.PRECONDITION_FAILED.value();

	public NotFoundException(String message) {
		super(status, message);
	}
}
