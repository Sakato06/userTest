package com.supralog.user.commons;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2562739973343262854L;

	public UserNotFoundException(String pseudo) {
	    super("Could not find user " + pseudo);
	  }
	}