package com.example.miniproject.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SetVipRequest {

	@NotNull
	private Long to;

	@NotNull
	private Long from;

	@NotNull
	private Boolean vip;
}
