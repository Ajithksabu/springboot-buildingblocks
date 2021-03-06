package com.stacksimplify.restservices.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

//controllers
@RestController
@Validated
@RequestMapping(value="/users")
public class UserContoller {
	//Autowire the UserService
	@Autowired
	private UserService userService;
	
	//get all users
	@GetMapping
	public List<User> getAllUsers(){
		
		return userService.getAllUsers();
		
	}
	
	//create users
	//@RequestBody annotation
	//@PostMapping annotation
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		
		try {
			userService.createUser(user);
			HttpHeaders headers=new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}
		catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
			
		}
		
	}
	
	//get user by id
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		}
		catch(UserNotFoundException ex){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
		
	}
	
	//Update user by id
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable Long id,@RequestBody User user) {
		try {
			return userService.updateUserById(id,user);
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	//Delete user by Id
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
	
	//Get user by username
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable String username) throws UserNameNotFoundException {
		User user= userService.getUserByUsername(username);
		
		if(user==null) {
			throw new UserNameNotFoundException("username '"+username+"' not found in repository");
		}
		return user;
	}
	

}
