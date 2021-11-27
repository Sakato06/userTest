package com.supralog.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
/**
 * 
 * <b>Use case for test.</b>
 * <p>
 *  Develop with SpringBoot.
 *  
 *  This project create database in memory. 
 *  
 *  Injection of one row (data injection.sql)
 *  The database is accessible at http://localhost:8080/h2 (change JDBC URL by jdbc:h2:mem:user) 
 *  <ul>
 *  <li> user : sa </li>
 *  <li> no password </li>
 *  </ul>
 *  
 * @author F.Anelli
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UserTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTestApplication.class, args);
	}

}
