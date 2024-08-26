package com.shine.api.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shine.api.model.Order;
import com.shine.api.model.OrderItem;
import com.shine.api.repository.OrderItemRepository;
import com.shine.api.repository.OrderRepository;

@RestController
@RequestMapping("api/orders")
public class OrderController {
	
	@Autowired private OrderRepository orderRepo;
	@Autowired private OrderItemRepository orderItemRepo;

	/* ******************************************************************************
	 * Order API
	 * 
	 */
	@GetMapping
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(Long id) {
		return orderRepo.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Order createOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
		return orderRepo.findById(id)
				.map(order -> {
					order.setOrderItems(orderDetails.getOrderItems());
					order.setPrice(orderDetails.getPrice());
					order.setQuantity(orderDetails.getQuantity());
					order.setUser(orderDetails.getUser());
					order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
					return ResponseEntity.ok(orderRepo.save(order));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
		Optional<Order> orderOptional = orderRepo.findById(id);
		if (orderOptional.isPresent()) {
			orderRepo.delete(orderOptional.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/* ******************************************************************************
	 * OrderItem API
	 * 
	 */
	@GetMapping("{orderId}/items")
	public List<OrderItem> getAllOrderItems(Long orderId) {
		return orderItemRepo.findOrderItemsByOrderId(orderId);
	}
	
	@GetMapping("items/{itemId}")
	public ResponseEntity<OrderItem> getOrderItemById( @PathVariable Long itemId) {
		return orderItemRepo.findById(itemId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/items")
	public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
		return orderItemRepo.save(orderItem);
	}
	
	@PutMapping("/items/{itemId}")
	public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long itemId, @RequestBody OrderItem orderItemDetails) {
		return orderItemRepo.findById(itemId)
				.map(orderItem -> {
					orderItem.setQuantity(orderItemDetails.getQuantity());
					return ResponseEntity.ok(orderItemRepo.save(orderItem));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("items/{itemId}")
	public ResponseEntity<Object> deleteOrderItem(@PathVariable Long itemId) {
		return orderItemRepo.findById(itemId)
				.map(orderItem -> {
					orderItemRepo.delete(orderItem);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
