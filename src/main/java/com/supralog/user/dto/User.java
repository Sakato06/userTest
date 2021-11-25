package com.supralog.user.dto;


import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Class for database User. 
 * {@id} mandatory
 * @author Check
 *
 */
@Data
public class User {
	@Id @GeneratedValue Long id;
	@NotNull String pseudo;
	@NotNull String pwd;
	@NotNull String email;
	@NotNull Date dateOfBirth;
	String name;
	String lastName;
	String address;
}
