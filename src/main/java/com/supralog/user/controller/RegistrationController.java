package com.supralog.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supralog.user.dto.User;
import com.supralog.user.interfaces.UserRepository;

@RestController
public class RegistrationController {
	 private final UserRepository repository;

	 RegistrationController(UserRepository repository) {
	    this.repository = repository;
	  }
	
	 @PostMapping("/registration")
	 User newUser(@RequestBody User newUser) {
		 return repository.save(newUser);
	 }
	
}
