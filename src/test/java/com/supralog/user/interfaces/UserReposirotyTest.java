package com.supralog.user.interfaces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.supralog.user.dto.User;
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserReposirotyTest {
	@Autowired TestEntityManager entityManager;
	@Autowired UserRepository userRepository;
	
	@Test
	public void userRepositoryTest_whenFindByPseudo_thenReturnUser() {
		
		//create persist user for test
		User userTest = new User();
		userTest.setPseudo("test");
		userTest.setPwd("pwd");
		userTest.setCountry("France");
		userTest.setEmail("test@test.com");
		userTest.setAge(18);
		
		entityManager.persist(userTest);
		entityManager.flush();
		
		//test
		Optional<User> userFound = userRepository.findByPseudo("test");
		assertThat(userFound.get().getPseudo())
	      .isEqualTo(userTest.getPseudo());
		
	}
	
	@Test
	public void userRepositoryTest_whenDontFindByPseudo_thenReturnEmpty() {
		assertTrue(userRepository.findByPseudo("test").isEmpty());	
	}

}
