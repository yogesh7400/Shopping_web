package com.abm.service;

import java.util.List;

import com.abm.exception.UserException;
import com.abm.entity.Address;
import com.abm.entity.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
		
	public User findUserByJwt(String jwt) throws UserException;
	
	public List<User> findAllUsers();
 

	


	


}
