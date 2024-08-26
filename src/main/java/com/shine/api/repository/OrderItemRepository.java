package com.shine.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.api.model.OrderItem;
import com.shine.api.model.User;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findOrderItemsByOrderId(Long orderId);
	User findUserByOrderId(Long orderId);
}
