package com.example.miniproject.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SetPositionPriority {

	private Integer id;
	@NotBlank
	private String name;
	@NotNull
	@Min(1)
	private Integer priority;
}
