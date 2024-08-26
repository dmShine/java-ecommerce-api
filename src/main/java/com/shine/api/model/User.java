package com.shine.api.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private long id;
	@Getter @Setter @Nonnull private String name;
	@Getter @Setter @Nonnull private String email;
	@Getter @Setter private String phoneNumber;
	@Getter @Setter private Timestamp createdAt;
	@Getter @Setter private Timestamp updatedAt;
	@Getter @Setter private Timestamp lastLogin;
	@Getter @Setter private boolean emailValidated;
	@Getter @Setter private boolean phoneValidated;
	@Getter @Setter private String bio;
	@Getter @Setter private String company;
    
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@Getter @Setter private Order order;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<UserAddress> addresses;
}
