package com.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.Retailer;
import com.abm.entity.User;
import com.abm.repo.RetailerRepository;
import com.abm.repo.UserRepository;

@RestController
@RequestMapping("/api/retailer")
public class RetailerController {
	
	
	    @Autowired
	    private RetailerRepository retailerRepository;
	    
	    @PostMapping("/register")
	    public Retailer registerRetailer(@RequestBody Retailer retailer) {
	        Retailer existingRetailer = retailerRepository.findByEmail(retailer.getEmail());
	        if (existingRetailer != null) {
	            throw new RuntimeException("Username already exists. Please choose another username.");
	        }
	        return retailerRepository.save(retailer);
	    }

	    @PostMapping("/login")
	    public Retailer loginUser(@RequestBody Retailer retailer) {
	    	Retailer existingRetailer = retailerRepository.findByEmail(retailer.getEmail());
	        if (existingRetailer != null && existingRetailer.getPassword().equals(retailer.getPassword())) {
	            return existingRetailer;
	        } else {
	            throw new RuntimeException("Invalid username or password. Please try again.");
	        }
	    }

	    @PostMapping("/reset-password")
	    public Retailer resetPassword(@RequestParam String email, @RequestParam String newPassword) {
	    Retailer existingRetailer = retailerRepository.findByEmail(email);
	        if (existingRetailer != null) {
	        	existingRetailer.setPassword(newPassword);
	            return retailerRepository.save(existingRetailer);
	        } else {
	            throw new RuntimeException("User not found. Password reset failed.");
	        }
	    }

	    
	    
	    
	    
	    
	    
	    
	
	
	

}
