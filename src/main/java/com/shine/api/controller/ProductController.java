package com.shine.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shine.api.model.Product;
import com.shine.api.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductByID(Long id) {
		return repo.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return repo.save(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		return repo.findById(id)
				.map(product -> {
					product.setName(productDetails.getName());
					product.setDescription(productDetails.getDescription()); 
					product.setPrice(productDetails.getPrice());
					return ResponseEntity.ok(repo.save(product));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		return repo.findById(id)
				.map(product -> {
					repo.delete(product);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
