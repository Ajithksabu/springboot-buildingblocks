package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	    //get all users
		@GetMapping
		public CollectionModel<User> getAllUsers() throws UserNotFoundException{
			
			List<User> allusers= userService.getAllUsers();
			
			for(User user:allusers) {
				//self link for each user
				Long userid=user.getUserid();
				Link selflink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				
				//Relationship link with get all orders
				CollectionModel<Order> orders=WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
				Link ordersLink=WebMvcLinkBuilder.linkTo(orders).withRel("All-orders");
				user.add(ordersLink);			
				
							}
			
			//Self link for get all users
			Link selfLinkForGetAllUsers=WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
			CollectionModel<User> finalResource=CollectionModel.of(allusers,selfLinkForGetAllUsers);
			return finalResource;
			
		}
		
		//get user by id
		@GetMapping("/{id}")
		public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
			
			try {
				Optional<User> userOptional=userService.getUserById(id);
				User user=userOptional.get();
				Long userid=user.getUserid();
				Link selflink=WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				EntityModel<User> finalResource=EntityModel.of(user);
				return finalResource;
			}
			catch(UserNotFoundException ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
			}
			
			
		}	

}
