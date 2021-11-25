package com.supralog.user.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supralog.user.dto.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
