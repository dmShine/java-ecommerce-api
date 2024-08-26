package com.shine.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
