package com.supralog.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.supralog.user.dto.User;
import com.supralog.user.interfaces.UserRepository;

/**
 * <b>Service Rest for request POST on database</b>
 * <p>
 * Only createUser is implemented at this time.
 * Dispatch exception if user is under 18 or not lived in France or pseudo is dupplicated
 * 
 * RequestMapping(value = "/registration")
 * 
 * @author Check
 *
 */
@RestController
@Validated
@RequestMapping(value = "/registration", method = {RequestMethod.GET, RequestMethod.PUT})
public class RegistrationController {
	private final UserRepository repository;
	Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	StopWatch watch = new StopWatch();

	RegistrationController(UserRepository repository) {
		this.repository = repository;
	}

	/**
	 * 
	 * PostMapping method to create new user. 
	 * PostMapping = "/newUser"
	 * 
	 * Exception will throw when:
	 * <ul>
	 * <li> Pseudo already exist </li>
	 * <li> Age under 18 </li>
	 * <li> Country isn't France </li>
	 * </ul>
	 * @param pseudo mandatory, if already exist throw a bad request
	 * @param pwd mandatory
	 * @param email mandatory
	 * @param age mandatory
	 * @param country mandatory
	 * @param name optional
	 * @param lastName optional
	 * @param gender optional, Others in default value
	 * @param address optional
	 * @return String
	 */
	@PostMapping("/newUser")
	String createUser(@RequestParam(name = "pseudo") String pseudo, @RequestParam(name = "pwd") String pwd, @RequestParam(name = "email") String email, @RequestParam(name = "age") String age,
			@RequestParam(name = "country") String country,@RequestParam(required = false, name = "name" ) String name,  @RequestParam(required = false,  name = "lastName" ) String lastName, 
			@RequestParam(defaultValue = "Others", name="gender") String gender,
			@RequestParam(required = false, name="address") String address) {

		watch.start();
		User newUser = new User();

		/* No dupplicate pseudo in database*/
		stopIfPseudoExist(pseudo);
		stopIfCountryNotAllowed(country);

		try {
			newUser.setPseudo(pseudo);
			newUser.setPwd(pwd);
			newUser.setEmail(email);
			newUser.setCountry(country);
			newUser.setGender(gender);
			newUser.setLast(lastName);
			newUser.setName(name);
			newUser.setAddress(address);
			newUser.setAge( Integer.parseInt(age));
			logger.info("Entrance for newUser {}",newUser.toString());
			repository.save(newUser);
		}
		finally {
			watch.stop();
		}


		return "New User Created";
	}

	private void stopIfCountryNotAllowed(String country) {
		if(!"FRANCE".equalsIgnoreCase(country)) {
			watch.stop();
			logger.info("User Registration in {} milliseconds",watch.getTotalTimeMillis());
			logger.info("Only France is allowed for country {}",country);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Only France is allowed for country");
		}
	
	}

	private void stopIfPseudoExist(String pseudo) {

		if(!repository.findByPseudo(pseudo).isEmpty()) {
			watch.stop();
			logger.info("User Registration in {} milliseconds",watch.getTotalTimeMillis());
			logger.info("User already exist {}",pseudo);
			throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already exist");
		}
	}
}
