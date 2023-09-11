package com.onlineeducationsystem.app.exceptions;

 

import org.springframework.http.HttpStatus;

 

import org.springframework.web.bind.annotation.ResponseStatus;

 

import lombok.AllArgsConstructor;

 

import lombok.Getter;

 

import lombok.NoArgsConstructor;

 

import lombok.Setter;

 

@NoArgsConstructor

 

@AllArgsConstructor

 

@Getter

 

@Setter

 

@ResponseStatus(code = HttpStatus.NOT_FOUND)

 

public class CourseNotFoundException extends Exception {

	public CourseNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

 

    

 

}