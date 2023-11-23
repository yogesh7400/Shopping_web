package com.abm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abm.entity.User;
import com.abm.exception.UserException;
import com.abm.repo.AddressRepository;
import com.abm.repo.UserRepository;
import com.abm.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private AddressRepository addressRepository;
    
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers() throws UserException {
        return userRepository.findAll();
    }
    
    @GetMapping("/fetchdetailsbyid")
    public User getUserById(@RequestParam Long id) {
        return  userRepository.findUserById(id);
    }
    

   
    
}
