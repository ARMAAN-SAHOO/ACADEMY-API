package com.armaan.academyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);

}
