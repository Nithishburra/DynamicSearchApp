package com.ashokit.Binding.Response;


import lombok.Data;

@Data
public class InsuranceResponse {

	private Integer planId;

	private String planName;

	private String holderName;

	private Long holderSsn;

	private String planStatus;

}
