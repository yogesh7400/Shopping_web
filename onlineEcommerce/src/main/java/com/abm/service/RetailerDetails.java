package com.abm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abm.entity.Retailer;
import com.abm.entity.User;
import com.abm.repo.RetailerRepository;
import com.abm.repo.UserRepository;

@Service
public class RetailerDetails implements UserDetailsService {
	
	private RetailerRepository retailerRepository;
	
	public RetailerDetails(UserRepository userRepository) {
		this.retailerRepository=retailerRepository;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Retailer retailer = retailerRepository.findByEmail(username);
		
		if(retailer == null) {
			throw new UsernameNotFoundException("user not found with email "+username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(retailer.getEmail(),retailer.getPassword(),authorities);
	}

}
