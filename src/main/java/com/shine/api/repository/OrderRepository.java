package com.shine.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
