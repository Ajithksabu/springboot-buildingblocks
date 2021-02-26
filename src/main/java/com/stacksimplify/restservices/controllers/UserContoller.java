package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserService;

//controllers
@RestController
public class UserContoller {
	//Autowire the UserService
	@Autowired
	private UserService userService;
	
	//get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return userService.getAllUsers();
		
	}
	
	//create users
	//@RequestBody annotation
	//@PostMapping annotation
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//get user by id
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
		
	}
	
	//Update user by id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable Long id,@RequestBody User user) {
		return userService.updateUserById(id,user);
	}
	
	//Delete user by Id
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
	
	//Get user by username
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	

}