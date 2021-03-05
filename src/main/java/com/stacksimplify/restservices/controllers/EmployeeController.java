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

import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.services.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
		@GetMapping("/employees")
		public List<Employee> getAllEmployees(){
			
			return employeeService.getAllEmployees();
			
		}
		
		
		@PostMapping("/employees")
		public Employee createEmployee(@RequestBody Employee employee) {
			return employeeService.createEmployee(employee);
		}
		
		
		@GetMapping("/employees/{empid}")
		public Optional<Employee> getEmployeeById(@PathVariable("empid") Long empid) {
			return employeeService.getEmployeeById(empid);
			
		}
		
		
		@PutMapping("/employees/{empid}")
		public Employee updateEmployeeById(@PathVariable Long empid,@RequestBody Employee employee) {
			return employeeService.updateEmployeeById(empid,employee);
		}
		
		
		@DeleteMapping("/employees/{empid}")
		public void deleteEmployeeById(@PathVariable Long empid) {
			employeeService.deleteEmployeeById(empid);
		}

}
