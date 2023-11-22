package com.abm.controller;     //new

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.Product;
import com.abm.entity.Retailer;
import com.abm.entity.User;
import com.abm.exception.ProductException;
import com.abm.exception.RetailerException;
import com.abm.repo.RetailerRepository;
import com.abm.repo.UserRepository;
import com.abm.request.LoginStatus;
import com.abm.request.RegistrationStatus;
import com.abm.service.RetailerService;

@RestController
@RequestMapping("/api/retailer")
@CrossOrigin
public class RetailerController {
	
	
	    @Autowired
	    private RetailerRepository retailerRepository;
	    @Autowired
	    private RetailerService retailerService;
	    
	    
	    @PostMapping("/register")
		public RegistrationStatus register(@RequestBody Retailer retailer) {
			try {
				long id = retailerService.register(retailer);
				//return "Customer registered!";
				RegistrationStatus status = new RegistrationStatus();
				status.setStatus(true);
				status.setMessageIfAny("Retailer registered!");
				status.setRetailerId(id);
				return status;
			}
			catch (RetailerException e) {
				RegistrationStatus status = new RegistrationStatus();
				status.setStatus(false);
				status.setMessageIfAny(e.getMessage());
				return status;
			}
		}

	    @PostMapping("/login")
		public LoginStatus login(@RequestBody Retailer retailer) {
			try {
				Retailer retailerDetails = retailerService.login(retailer.getEmail(), retailer.getPassword());
				LoginStatus status = new LoginStatus();
				status.setStatus(true);
				status.setName(retailer.getEmail());
				status.setRetailerId(retailerDetails.getId());
				return status;
			}
			catch (RetailerException e) {
				LoginStatus status = new LoginStatus();
				status.setStatus(false);
				status.setMessageIfAny(e.getMessage());
				return status;
			}
		}
	    
	    @GetMapping("/{retailerId}")
	    public List<Product> findByRetailerId(@PathVariable Long retailerId) {
	        return retailerService.findByRetailerId(retailerId);
	    }

}
