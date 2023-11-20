package com.abm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.entity.Address;
import com.abm.entity.User;
import com.abm.exception.UserException;
import com.abm.repo.AddressRepository;
import com.abm.repo.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	
	

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()){
			return user.get();
		}
		throw new UserException("user not found with id "+userId);
	}




	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAllByOrderByCreatedAtDesc();
	}
	
	 public User getDefaultUser() throws UserException {
	        // For testing purposes, you might want to return a default user or throw an exception if not found.
	        // Replace this logic with your own requirements.
	        // Example: Retrieve a user with a known ID, or create a new user for testing.

	        // For illustration, let's assume there is a method in the repository to find a user by ID.
	        // You should replace this with the actual logic that fits your application.

	        // Replace 1L with the ID of the default user, or adjust it based on your database content.
	        return userRepository.findById(1L).orElseThrow(() -> new UserException("Default user not found"));
	    }




	@Override
	public List<Address> getUserAddress(Long userId) {
		 Optional<User> userOptional = userRepository.findById(userId);

	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            // Assuming the user has a reference to the address or an address ID
	            // Adjust the logic based on your actual data model
	            return user.getAddresses();
	        } else {
	            // Handle the case where the user with the specified ID is not found
	            // You might throw an exception or return null based on your requirements
	            return null;
	        }
	}
	 

}
