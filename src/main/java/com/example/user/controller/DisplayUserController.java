package com.example.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.commons.UserNotFoundException;
import com.example.user.dto.User;
import com.example.user.interfaces.UserRepository;

/**
 * <b>Service Rest for request GET on database</b>
 * <p>
 * Only showUser is implemented at this time.
 * Dispatch exception if user's informations, otherwise dispatch custom error UserNotFoundException
 * 
 * @author Check
 *
 */
@RestController
public class DisplayUserController {
	@Autowired
	private final UserRepository repository;

	Logger logger = LoggerFactory.getLogger(DisplayUserController.class);
	StopWatch watch = new StopWatch();

	DisplayUserController(UserRepository repository) {
		this.repository = repository;
	}
	/**
	 *  
	 *  Mapping = /user/{pseudo}
	 *  PathVariable pseudo mandatory
	 * 
	 * @param pseudo request User send by pathvariable
	 * @return User found or UserNotFoundException
	 */
	@GetMapping("/user/{pseudo}")
	public User showUser(@PathVariable String pseudo) {
		User requestUser;
		logger.info("Entrance of showUser for user : {}", pseudo);
		watch.start();
		try{
			requestUser = repository.findByPseudo(pseudo).orElseThrow(() -> new UserNotFoundException(pseudo));
		}
		finally {
			watch.stop();
			logger.info("Exit showUser in {} millisecondes ",watch.getTotalTimeMillis());
		}

		logger.info("Result of request : {}",requestUser.toString());
		return requestUser;

	}
}
