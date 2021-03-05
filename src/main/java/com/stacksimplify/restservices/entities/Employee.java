package com.stacksimplify.restservices.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue
	private Long empid;
	private String fname;
	private String lname;
	private Long age;
	private String designation;
	private String address;
	
	public Employee() {
		
	}

	public Employee(Long empid, String fname, String lname, Long age, String designation, String address) {
		super();
		this.empid = empid;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.designation = designation;
		this.address = address;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", fname=" + fname + ", lname=" + lname + ", age=" + age + ", designation="
				+ designation + ", address=" + address + "]";
	}
	
		

}
