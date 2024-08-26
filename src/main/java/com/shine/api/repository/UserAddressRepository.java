package com.shine.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.api.model.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
	List<UserAddress> findByUserId(Long userId);
}
