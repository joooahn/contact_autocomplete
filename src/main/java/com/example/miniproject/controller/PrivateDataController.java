package com.example.miniproject.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.miniproject.model.request.SetVipRequest;
import com.example.miniproject.service.PrivateDataService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
@CrossOrigin("*")
@RequestMapping("private")
public class PrivateDataController {

	private final PrivateDataService privateDataService;

	public PrivateDataController(PrivateDataService privateDataService) {
		this.privateDataService = privateDataService;
	}

	@PostMapping("vip")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void setVip(@RequestBody @Valid SetVipRequest setVipRequest) {
		privateDataService.setVip(setVipRequest.getTo(), setVipRequest.getFrom(), setVipRequest.getVip());
	}

	@PostMapping("transmission")
	public void insertTransmission(@RequestBody @Validated EmailLog emailLog) {
		privateDataService.insertTransmission(emailLog.getTo(), emailLog.getFrom());
	}

	@Getter
	@Setter
	@NoArgsConstructor(access = AccessLevel.PACKAGE)
	static class EmailLog {
		@NotBlank(message = "수신자는 필수입니다.")
		private String to;
		@NotBlank(message = "발신자는 필수입니다.")
		private String from;
	}
}
