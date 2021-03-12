package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;




//entity
@Entity
@Table(name="user")
public class User extends RepresentationModel<User>{
	
	@Id
	@GeneratedValue
	private Long userid;
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
	
	//referencing side
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	
	


//	No argument constructors
public User() {
		
}

//Constructor with fields
public User(Long userid, @NotEmpty(message = "Username is Mandatory field. Please provide username") String username,
		@Size(min = 2, message = "FirstName should have atleast 2 characters") String firstname, String lastname,
		String email, String role, String ssn, List<Order> orders) {
	super();
	this.userid = userid;
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.role = role;
	this.ssn = ssn;
	this.orders = orders;
}

//Getters and Setters

public Long getUserid() {
	return userid;
}

public void setUserid(Long userid) {
	this.userid = userid;
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


public List<Order> getOrders() {
	return orders;
}

public void setOrders(List<Order> orders) {
	this.orders = orders;
}



//To string
@Override
public String toString() {
	return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
			+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + "]";
}





 

}
