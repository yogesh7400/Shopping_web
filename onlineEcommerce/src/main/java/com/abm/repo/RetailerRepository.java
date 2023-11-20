package com.abm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.entity.Retailer;
import com.abm.entity.User;

public interface RetailerRepository extends JpaRepository<Retailer, Long> {

	Retailer findByEmail(String email);
	
	

}
