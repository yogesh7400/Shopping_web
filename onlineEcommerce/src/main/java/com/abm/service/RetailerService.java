package com.abm.service;   //new

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.entity.Retailer;
import com.abm.exception.RetailerException;
import com.abm.repo.RetailerRepository;


@Service
public class RetailerService {
	
	@Autowired
	private RetailerRepository retailerRepository;
	
	public long register(Retailer retailer) {
		Long count = retailerRepository.findIfRetailerExists(retailer.getEmail());
		if(count == 0) {
			Retailer r = retailerRepository.save(retailer);
			return r.getId();
		}	
		else
			throw new RetailerException("Retailer already exists!");
	}
	
	public Retailer login(String email, String password) {
		Optional<Retailer> c = retailerRepository.findByEmailAndPassword(email, password);
		if(c.isPresent())
			return c.get();
		else
			throw new RetailerException("Invalid Email/Password!");
	}
	
	public void deleteAccountById(Long id) {
		retailerRepository.deleteById(id);
    }

}
