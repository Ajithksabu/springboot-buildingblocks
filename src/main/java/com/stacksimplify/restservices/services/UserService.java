package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		
		User existingUser=userRepository.findByUsername(user.getUsername());
		
		if(existingUser!=null) {
			throw new UserExistsException("User already exist");
		}
		
		return userRepository.save(user);
	}
	
	//get user by id
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user=userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in repository");
		}
		return user;
	}
	
	//Update user by Id
	public User updateUserById(Long id,User user) throws UserNotFoundException {
		
        Optional<User> optionalUser=userRepository.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in repository,check the request id");
		}
		
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete user by Id
	public void deleteUserById(Long id) {
		 Optional<User> optionalUser=userRepository.findById(id);
			
			if(!optionalUser.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in repository,check the request id");
			}
			userRepository.deleteById(id);
	}
	
	//Get user by username
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
