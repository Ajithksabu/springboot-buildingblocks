package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders
		@GetMapping("/{userid}/orders")
		public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
			
			Optional<User> user=userRepository.findById(userid);
			
			if(!user.isPresent())
				throw new UserNotFoundException("User not found");
			List<Order> allorders= user.get().getOrders();
			CollectionModel<Order> finalResources=CollectionModel.of(allorders);
			return finalResources;
			
		}

}