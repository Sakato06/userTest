package com.supralog.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.supralog.user.commons.UserNotFoundException;
import com.supralog.user.dto.User;
import com.supralog.user.interfaces.UserRepository;


@RestController
public class DisplayUser {
	@Autowired
	private final UserRepository repository;

	Logger logger = LoggerFactory.getLogger(DisplayUser.class);
	StopWatch watch = new StopWatch();

	DisplayUser(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/user/{pseudo}")
	@ResponseBody
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
	@GetMapping("/")
	public String hello() {
		return "service is up and running";    
	}
}
