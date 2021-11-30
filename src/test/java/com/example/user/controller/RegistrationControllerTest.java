package com.example.user.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.user.controller.RegistrationController;
import com.example.user.dto.User;
import com.example.user.interfaces.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

	@MockBean UserRepository userRepository;
	@Autowired private MockMvc mvc;

	@Before
	public void registrationController_initUser() {
		User userTest = new User();
		userTest.setPseudo("test");
		userTest.setPwd("pwd");
		userTest.setCountry("France");
		userTest.setEmail("test@test.com");
		userTest.setAge(18);
		when(userRepository.findByPseudo("test")).thenReturn(Optional.of(userTest));
	}

	@Test
	public void registrationControllerTest_whenSaveIsOk_thenReturnOk() throws Exception {
		mvc.perform(post("/registration/newUser?pseudo=test2&pwd=test&age=18&email=test@test.com&country=France")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	@Test
	public void registrationControllerTest_whenDupplicatePseudo_thenReturnError() throws Exception {
		mvc.perform(post("/registration/newUser?pseudo=test&pwd=test&age=18&email=test@test.com&country=France")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError())
		.andExpect(status().reason(containsString("User already exist")));
	}

	@Test
	public void registrationControllerTest_catchMandatoryValues_thenReturnError() throws Exception {
		mvc.perform(post("/registration/newUser?")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError())
		.andExpect(content().string(containsString("Required request parameter 'pseudo'")));;
	}

	@Test
	public void registrationControllerTest_ifNotInFrance_thenReturnError() throws Exception {
		mvc.perform(post("/registration/newUser?pseudo=test1&pwd=test&age=18&email=test@test.com&country=USA")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError())
		.andExpect(status().reason(containsString("Only France is allowed for country")));
	}


}
