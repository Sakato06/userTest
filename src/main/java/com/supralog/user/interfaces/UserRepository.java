package com.supralog.user.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supralog.user.dto.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByPseudo(String pseudo);
}
