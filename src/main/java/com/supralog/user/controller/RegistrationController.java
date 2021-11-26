package com.supralog.user.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supralog.user.dto.User;
import com.supralog.user.interfaces.UserRepository;

@RestController
@RequestMapping("registration")
public class RegistrationController {
	 private final UserRepository repository;
	 Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	 StopWatch watch = new StopWatch();
	 
	 RegistrationController(UserRepository repository) {
	    this.repository = repository;
	  }
	
	 @PostMapping("/registration/{pseudo}/{pwd}/{email}/{country}/{age}/{name}/{lastName}/{gender}/{address}")
	 String createUser(@PathVariable String pseudo, @PathVariable String pwd, @PathVariable String email,@PathVariable String age,
			 @PathVariable(required = false) String name, @PathVariable(required = false) String lastName, @PathVariable Optional<String> gender,
			 @PathVariable(required = false) String address) {
		 
		 watch.start();
		 User newUser = new User();
		 
		 	
	
		/* optional value with default value */ 
		 if(gender.isPresent()) 
			 newUser.setGender(gender.get());
		 else newUser.setGender("Others");
		 
		 /* age < 18 is forbidden */
	
		 
		 
		 repository.save(newUser);
		 watch.stop();
		 return "New User Created";
	 }
	
}
