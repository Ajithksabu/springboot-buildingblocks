package com.stacksimplify.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//entity
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	//Bean validation
	@NotEmpty(message = "Username is Mandatory field. Please provide username")
	@Column(length=50,nullable=false,unique=true)
	private String username;
	//Bean validation
	@Size(min=2, message="FirstName should have atleast 2 characters")
	@Column(length=50,nullable=false)
	private String firstname;
	@Column(length=50,nullable=false)
	private String lastname;
	@Column(length=50,nullable=false)
	private String email;
	@Column(length=50,nullable=false)
	private String role;
	@Column(length=50,nullable=false,unique=true)
	private String ssn;
	
	
//	No argument constructors
public User() {
		
}

//Constructor with fields
public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
	super();
	this.id = id;
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.role = role;
	this.ssn = ssn;
}

//Getters & Setters
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getSsn() {
	return ssn;
}

public void setSsn(String ssn) {
	this.ssn = ssn;
}

//To string
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
			+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
}






    

}
