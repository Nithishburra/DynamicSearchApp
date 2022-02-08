package com.ashokit.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.Entity.InsuranceEntity;


public interface InsuranceRepository extends JpaRepository<InsuranceEntity,Serializable>{
	
	@Query(value="select distinct planName from InsuranceEntity")
	public List<String> getbyplanname();
	
	@Query(value = "select distinct planStatus from InsuranceEntity")
	public List<String> getbyplanstatus();
	
}
