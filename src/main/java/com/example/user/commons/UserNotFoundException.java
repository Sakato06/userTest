package com.example.user.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
/**
 * <b>Custom error for bad request</b>
 * <p>
 * Custom error extends ResponseStatusException when an user isn't found
 * @author F.Anelli
 *
 */
public class UserNotFoundException extends ResponseStatusException  {

	private static final long serialVersionUID = 2562739973343262854L;
	private final Logger logger = LoggerFactory.getLogger(UserNotFoundException.class);
	/**
	 * Throw error 404 instead of runtime exception
	 * @param pseudo User not found
	 */
	public UserNotFoundException(String pseudo) {
	    super(HttpStatus.NOT_FOUND,String.format("Couldn't find user : %s", pseudo));
	    logger.error("Couldn't find user : {}", pseudo);
	  }
	}