package com.example.miniproject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class CoworkDepartment {
	@NotNull(message = "사용자 id는 필수입니다.")
	private Long userId;
	@NotNull(message = "부서 id는 필수입니다.")
	private Long departmentId;

	public CoworkDepartment(@NotNull(message = "사용자 id는 필수입니다.") Long userId,
		@NotNull(message = "부서 id는 필수입니다.") Long departmentId){
		this.userId = userId;
		this.departmentId = departmentId;
	}
}
