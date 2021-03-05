package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
		public List<Employee> getAllEmployees(){
			return employeeRepository.findAll();
		}
		
		
		public Employee createEmployee(Employee employee) {
			return employeeRepository.save(employee);
		}
		
		
		public Optional<Employee> getEmployeeById(Long empid) {
			Optional<Employee> employee=employeeRepository.findById(empid);
			return employee;
		}
		
		
		public Employee updateEmployeeById(Long empid,Employee employee) {
			employee.setEmpid(empid);
			return employeeRepository.save(employee);
		}
		
		
		public void deleteEmployeeById(Long empid) {
			if(employeeRepository.findById(empid).isPresent()) {
				employeeRepository.deleteById(empid);
			}
		}

}
