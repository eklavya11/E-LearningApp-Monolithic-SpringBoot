package com.onlineeducationsystem.app.entity;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;





@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	@NotEmpty(message = "username is required")
	private String username;
	@Size(min = 8)
	private String password;
	@NotEmpty(message = "firstname is required")
	private String firstname;
	@NotEmpty(message = "lastname is required")
	private String lastname;
	@NotEmpty(message = "Email is required")
	@Column(unique = true)
	private String email;
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number is invalid")
	private String phone;
	@OneToMany
	private List<CourseEntity> courses = new ArrayList<>();
	
	
	
	

    

	

	
	

	

	

	

//	public Student(int id, @NotEmpty(message = "username is required") String username,
//			@Size(min = 8) String password, @NotEmpty(message = "firstname is required") String firstname,
//			@NotEmpty(message = "lastname is required") String lastname,
//			@NotEmpty(message = "Email is required") String email,
//			@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number is invalid") String phone
//			) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//		this.phone = phone;
//		
//	}
	



	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Student(int id, @NotEmpty(message = "username is required") String username, @Size(min = 8) String password,
		@NotEmpty(message = "firstname is required") String firstname,
		@NotEmpty(message = "lastname is required") String lastname,
		@NotEmpty(message = "Email is required") String email,
		@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number is invalid") String phone,
		List<CourseEntity> courses) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.phone = phone;
	this.courses = courses;
}
	
	



	public List<CourseEntity> getCourses() {
		return courses;
	}



	public void setCourses(List<CourseEntity> courses) {
		this.courses = courses;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	

	
    
	
	

	

	





}
