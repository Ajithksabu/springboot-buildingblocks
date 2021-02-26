package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

//Service
@Service
public class UserService {
    //Autowire UserReository
	@Autowired
	private UserRepository userRepository;
	 
	//get all users method
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//create user method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	//get user by id
	public Optional<User> getUserById(Long id) {
		Optional<User> user=userRepository.findById(id);
		return user;
	}
	
	//Update user by Id
	public User updateUserById(Long id,User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete user by Id
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	//Get user by username
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
