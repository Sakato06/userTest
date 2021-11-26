package com.supralog.user.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supralog.user.dto.User;
/**
 * <b>Repository for database access.</b>
 * <p>
 * Standard repository extends JpaRepository
 * 
 * @author F.Anelli
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * Method for select User by pseudo
	 * @param pseudo  User to find
	 * @return An user if found, empty otherwise
	 */
	Optional<User> findByPseudo(String pseudo);
}
