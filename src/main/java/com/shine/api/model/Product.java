package com.shine.api.model;

import java.sql.Timestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private long id;
	@Getter @Setter private String name;
	@Getter @Setter private String description;
	@Getter @Setter @Nonnull private double price;
	@Getter @Setter private boolean published;
	@Getter @Setter @Nonnull private String sku;
	@Getter @Setter private Timestamp createdAt;
	@Getter @Setter private Timestamp updatedAt;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Product product;
}
