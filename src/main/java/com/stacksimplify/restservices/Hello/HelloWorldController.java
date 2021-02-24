package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
public class HelloWorldController {
//Simple method
//	@RequestMapping(method = RequestMethod.GET,path="/helloworld");
	@GetMapping("/helloworld1")
	public String helloworld() {
		return "Hello world";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloworldbean() {
		return new UserDetails("Ajith","K Sabu","Cherthala");
	}
}
