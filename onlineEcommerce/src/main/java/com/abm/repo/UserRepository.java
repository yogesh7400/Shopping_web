package com.abm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	 
	public User findByEmail(String email);
	
	public User findById(User id);

	public List<User> findAllByOrderByCreatedAtDesc();
	
	public User findUserById(Long id);

}
