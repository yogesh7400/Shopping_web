package com.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abm.config.JwtTokenProvider;
import com.abm.entity.Retailer;
import com.abm.entity.User;
import com.abm.exception.UserException;
import com.abm.repo.RetailerRepository;
import com.abm.repo.UserRepository;
import com.abm.request.LoginRequest;
import com.abm.response.AuthResponse;
import com.abm.service.CartService;
import com.abm.service.CustomUserDetails;
import com.abm.service.RetailerDetails;
import com.abm.uni.UserRole;



@RestController
@RequestMapping("/retailer")
public class RetailerAuthController {

	@Autowired
	private RetailerRepository retailerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> createUserHandler(@Validated @RequestBody Retailer retailer) throws UserException{
		
		  	String email = retailer.getEmail();
	        String password = retailer.getPassword();
	        String firstName=retailer.getName();
	        String location=retailer.getLocation();
	        
	        
	        Retailer isEmailExist=retailerRepository.findByEmail(email);

	        // Check if user with the given email already exists
	        if (isEmailExist!=null) {
	        	
	            throw new UserException("Email Is Already Used With Another Account");
	        }

	        // Create new user
//			User createdUser= new User();
	        Retailer r=new Retailer();
			r.setEmail(email);
			r.setName(firstName);
			r.setLocation(location);
	        r.setPassword(passwordEncoder.encode(password));
	        
	        Retailer retailrsave= retailerRepository.save(r);
	        
	        

	        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        
	        String token = jwtTokenProvider.generateToken(authentication);

	        AuthResponse authResponse= new AuthResponse(token,true);
			
	        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
	    String email = loginRequest.getEmail();
	    String password = loginRequest.getPassword();
	    
	    Retailer retailer = retailerRepository.findByEmail(email);

	    if (retailer == null || !passwordEncoder.matches(password, retailer.getPassword())) {
	        throw new BadCredentialsException("Invalid email or password");
	    }

	    String token = jwtTokenProvider.generateToken(retailer); // Generate token for the retailer

	    AuthResponse authResponse = new AuthResponse();
	    authResponse.setStatus(true);
	    authResponse.setJwt(token);
	    
	    return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}
}
