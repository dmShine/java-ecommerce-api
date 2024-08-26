package com.shine.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItem {
	@Getter @Setter private Long id;
	@Getter @Setter private Double price;
	@Getter @Setter private Double quantity;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
}
