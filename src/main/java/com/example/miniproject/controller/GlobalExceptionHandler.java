package com.example.miniproject.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.exception.ContactException;
import com.example.miniproject.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(ContactException.class)
	public ErrorResponse contactExceptionHandler(HttpServletResponse response, ContactException ex) {
		response.setStatus(ex.getStatus());
		return new ErrorResponse(ex.getMessage());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException exception) {
		return new ErrorResponse("파라미터 형식 불일치");
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse httpMessageNotReadableExceptionHandler(HttpMediaTypeNotSupportedException exception) {
		return new ErrorResponse("파라미터 형식을 읽을 수 없음");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
		return new ErrorResponse("유효하지 않은 파라미터: " + exception.getBindingResult().getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse notFoundExceptionHandler(Exception exception) {
		log.error("error", exception);
		return new ErrorResponse("내부 오류");
	}
}
