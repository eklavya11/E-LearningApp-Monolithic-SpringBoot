package com.onlineeducationsystem.app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AdminException extends Exception{
	private static final Logger logger =  LoggerFactory.getLogger(AdminException.class);
	private static final long serialVersionUID = 1L;

	
	public AdminException() {
	
		// TODO Auto-generated constructor stub
	}

	public AdminException(String message){
		
    	super(message);
    	logger.info(message);
    }
}
