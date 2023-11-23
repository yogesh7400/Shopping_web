package com.abm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.abm.entity.Retailer;

import com.abm.repo.RetailerRepository;

@RestController
@RequestMapping("/api/retailers")
@CrossOrigin
public class AdminRetailerController {

    @Autowired
    private RetailerRepository retailerRepository;

    @GetMapping("/all")
	public List<Retailer> findAllReatiler(){
		return retailerRepository.findAll();
	}

}
