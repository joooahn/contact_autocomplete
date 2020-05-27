package com.example.miniproject.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublicData {

	private Long id;

	private String name;

	private String email;

	private int positionId;

	private String department;

	@Builder
	public PublicData(String name, String email, int positionId) {
		this.name = name;
		this.email = email;
		this.positionId = positionId;
	}
}
