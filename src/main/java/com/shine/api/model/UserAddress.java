package com.shine.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
    
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	@Getter @Setter private User user;
	
	@Getter @Setter private String addressLine1;
	@Getter @Setter private String addressNumber;
	@Getter @Setter private String addressLine2;
	@Getter @Setter private String postalCode;
	@Getter @Setter private String city;
	@Getter @Setter private String state;
	@Getter @Setter private String country;
	@Getter @Setter private String phoneNumber;
}
