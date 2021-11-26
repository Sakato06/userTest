package com.supralog.user.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * <b>User is the Entity for the database User.</b>
 * <p>
 * One user is defined by :
 * <ul>
 * <li>An unique identifier assigned when creating.</li>
 * <li>A pseudo, can be changed but unique in the database, mandatory</li>
 * <li>An email, can be changed, mandatory</li>
 * <li>An age, can be changed, mandatory, must be over 18</li>
 * <li>A country, mandatory, at this time only France is allowed</li>
 * <li>A name, optional</li>
 * <li>A last name, optional</li>
 * <li>An address, optional</li>
 * <li>A gender, optional, default value set to Others</li>
 * </ul>
 * 
 * This method use lombok.Data and javax.validation for controls.
 * 
 * @author F.Anelli
 * @version 1.0
 */
@Data
@Entity
@Table(name = "User")
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@NotNull String pseudo;
	@NotNull(message = "Fill your password") String pwd;
	@NotNull(message = "Fill your email") String email;
	@NotNull(message = "Fill your age") @Min(18) Integer age;
	@NotNull(message = "Fill your country") String country;
	String name;
	String last;
	String address;
	String gender;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", pwd=" + pwd + ", email=" + email + ", age=" + age
				+ ", name=" + name + ", last=" + last + ", address=" + address + ", country=" + country + ", gender="
				+ gender + "]";
	}
}
