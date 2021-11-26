package com.supralog.user.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2562739973343262854L;
	Logger logger = LoggerFactory.getLogger(UserNotFoundException.class);
	
	public UserNotFoundException(String pseudo) {
	    super(HttpStatus.NOT_FOUND,String.format("Couldn't find user : %s", pseudo));
	    logger.error("Couldn't find user : {}", pseudo);
	  }
	}