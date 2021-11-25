package com.supralog.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.supralog.user.commons.UserNotFoundException;
import com.supralog.user.dto.User;
import com.supralog.user.interfaces.UserRepository;

@RestController
public class DisplayUser {
	 private final UserRepository repository;

	 DisplayUser(UserRepository repository) {
	    this.repository = repository;
	  }
	
	 @GetMapping("/user/{pseudo}")
	 User newUser(@PathVariable String pseudo) {
		 return null;
		 //return repository.findById(pseudo).orElseThrow(() -> new UserNotFoundException(id));
	 }
}
