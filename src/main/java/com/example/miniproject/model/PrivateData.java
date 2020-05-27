package com.example.miniproject.model;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivateData {

	private Long to;

	private Long from;

	private LocalDateTime emailSendDate;

	private Boolean vip;

	@Builder
	public PrivateData(Long to, Long from, LocalDateTime emailSendDate, Boolean vip) {
		this.to = to;
		this.from = from;
		this.emailSendDate = emailSendDate;
		this.vip = vip;
	}
}
