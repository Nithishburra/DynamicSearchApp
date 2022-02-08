package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.Binding.Request.InsuranceRequest;
import com.ashokit.Binding.Response.InsuranceResponse;
import com.ashokit.Entity.InsuranceEntity;
import com.ashokit.Repository.InsuranceRepository;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private InsuranceRepository insrepo;

	@Override
	public List<InsuranceResponse> bySearch(InsuranceRequest request) {
		InsuranceEntity entity = new InsuranceEntity();

		if (request!=null &&request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}

		if (request!=null &&request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		Example<InsuranceEntity> of = Example.of(entity);
		List<InsuranceEntity> findall = insrepo.findAll(of);
		List<InsuranceResponse> plans = new ArrayList<>();
		for (InsuranceEntity plan : findall) {
			InsuranceResponse insrep = new InsuranceResponse();
			BeanUtils.copyProperties(plan, insrep);
			plans.add(insrep);
		}
		return plans;
	}

	@Override
	public List<String> getbyplanname() {
		return insrepo.getbyplanname();

	}

	@Override
	public List<String> getbyplanstatus() {
		return insrepo.getbyplanstatus();
	}

}
