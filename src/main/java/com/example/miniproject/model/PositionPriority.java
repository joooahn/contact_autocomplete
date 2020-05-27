package com.example.miniproject.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionPriority {

	private int id;
	private String name;
	private int priority;

	@Builder
	public PositionPriority(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}
}
