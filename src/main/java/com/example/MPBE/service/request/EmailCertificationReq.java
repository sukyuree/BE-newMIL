package com.example.MPBE.service.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class EmailCertificationReq {

	@NotNull
	private String email;
}
