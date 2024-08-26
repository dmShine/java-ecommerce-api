package com.shine.api.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
	@Getter @Setter private Long id;
	@Getter @Setter private Timestamp createdAt;
	@Getter @Setter private Double price;
	@Getter @Setter private Double quantity;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@Getter @Setter private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@Getter @Setter private List<OrderItem> orderItems;
}
