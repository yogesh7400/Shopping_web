package com.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.User;
import com.abm.repo.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	 @Autowired
	    private UserRepository userRepository;

	    @PostMapping("/register")
	    public User registerUser(@RequestBody User user) {
	        User existingUser = userRepository.findByEmail(user.getEmail());
	        if (existingUser != null) {
	            throw new RuntimeException("Username already exists. Please choose another username.");
	        }
	        return userRepository.save(user);
	    }

	    @PostMapping("/login")
	    public User loginUser(@RequestBody User user) {
	        User existingUser = userRepository.findByEmail(user.getEmail());
	        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
	            return existingUser;
	        } else {
	            throw new RuntimeException("Invalid username or password. Please try again.");
	        }
	    }
	    
	    @PostMapping("/reset-password")
	    public User resetPassword(@RequestParam String email, @RequestParam String Password) {
	    	System.out.println(email+" "+Password);
	        User existingUser = userRepository.findByEmail(email);
	        if (existingUser != null) {
	            existingUser.setPassword(Password);
	            return userRepository.save(existingUser);
	        } else {
	            throw new RuntimeException("User not found. Password reset failed.");
	        }
	    }

}
