package com.example.user.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.user.controller.DisplayUserController;
import com.example.user.dto.User;
import com.example.user.interfaces.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(DisplayUserController.class)
public class DisplayUserControllerTest {

	@MockBean UserRepository userRepository;
	@Autowired private MockMvc mvc;

	@Test
	public void displayUser_whenFindByPseudo_thenReturnUser() throws Exception {
		//create user for test
		User userTest = new User();
		userTest.setPseudo("test");
		userTest.setPwd("pwd");
		userTest.setCountry("France");
		userTest.setEmail("test@test.com");
		userTest.setAge(18);

		when(userRepository.findByPseudo("test")).thenReturn(Optional.of(userTest));

		mvc.perform(get("/user/test")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().json("{\"pseudo\":\"test\"}"));
	}

	@Test
	public void displayUser_whenPseudoNotFound_thenReturnErrorMessage() throws Exception {	
		mvc.perform(get("/user/test")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().is4xxClientError())
			      .andExpect(content().string(containsString("Couldn't find user : test")));;
	}

}
