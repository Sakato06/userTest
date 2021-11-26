package com.supralog.user.dto;


import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Class for database User. 
 * {@id} mandatory
 * @author Check
 *
 */
@Data
@Entity
@Table(name = "User")
public class User {
	@GeneratedValue Long id;
	@Id @NotNull String pseudo;
	@NotNull String pwd;
	@NotNull String email;
	@NotNull Date birthday;
	String name;
	String last;
	String address;
	@NotNull String country;
	String gender;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", pwd=" + pwd + ", email=" + email + ", birthday=" + birthday
				+ ", name=" + name + ", last=" + last + ", address=" + address + ", country=" + country + ", gender="
				+ gender + "]";
	}
}
