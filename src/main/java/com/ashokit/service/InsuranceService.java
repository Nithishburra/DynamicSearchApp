package com.ashokit.service;

import java.util.List;


import com.ashokit.Binding.Request.InsuranceRequest;
import com.ashokit.Binding.Response.InsuranceResponse;

public interface InsuranceService {

	public List<InsuranceResponse> bySearch(InsuranceRequest request);

	public List<String> getbyplanname();

	public List<String> getbyplanstatus();

}
