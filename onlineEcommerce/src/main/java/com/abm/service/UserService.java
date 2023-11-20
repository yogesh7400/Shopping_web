package com.abm.service;

import java.util.List;

import com.abm.exception.UserException;
import com.abm.entity.Address;
import com.abm.entity.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public List<User> findAllUsers();

	public User getDefaultUser() throws UserException;

	public List<Address> getUserAddress(Long userId);

	


	


}
