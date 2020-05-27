package com.example.miniproject.model.exception;

import lombok.Getter;

@Getter
public abstract class ContactException extends RuntimeException {

	private int status;

	public ContactException(int status, String message) {
		super(message);
		this.status = status;
	}
}
