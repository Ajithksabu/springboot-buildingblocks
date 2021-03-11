package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		
		Optional<User> user=userRepository.findById(userid);
		
		if(!user.isPresent())
			throw new UserNotFoundException("User not found");
		return user.get().getOrders();
		
	}
	
	
	//create orders
	//isPresent will return true/false but get() will return the value
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid,@RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional=userRepository.findById(userid);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User not found");
		
		User user=userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);		
		
	}

	//get orders by order id
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable("userid") Long userid,@PathVariable("orderid") Long orderid) throws UserNotFoundException {
		
		Order newOrder = null;
		Optional<User> user=userRepository.findById(userid);
		
		if(!user.isPresent())
			throw new UserNotFoundException("User not found");
		
		Optional<Order> order=orderRepository.findById(orderid);
		
		if(order.isPresent()) {
			if(order.get().getUser().getId()!=userid) {
				System.out.println("order not exist");
			}
			else {
				newOrder= order.get();
			}
			
		}	
		return newOrder;
				
		
		    
				
			
		
			
		
		
		
	}

}
	

	
	

