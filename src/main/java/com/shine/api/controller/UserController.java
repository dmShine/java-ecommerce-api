package com.shine.api.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.shine.api.repository.UserAddressRepository;
import com.shine.api.repository.UserRepository;
import com.shine.api.model.User;
import com.shine.api.model.UserAddress;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired private UserRepository userRepo;
	@Autowired private UserAddressRepository userAddressRepo;
	
	/* ******************************************************************************
	 * User API
	 * 
	 */
	@GetMapping
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable Long id) {
		return userRepo.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		return userRepo.findById(id)
				.map(user -> {					
					user.setName(userDetails.getName());
					user.setEmail(userDetails.getEmail());
					user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
					user.setEmailValidated(userDetails.isEmailValidated());
					user.setPhoneValidated(userDetails.isPhoneValidated());
					user.setBio(userDetails.getBio());
					user.setCompany(userDetails.getCompany());
					return ResponseEntity.ok(userRepo.save(user));
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.delete(user);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
	}
	
	/* ******************************************************************************
	 * UserAddress API
	 * 
	 */
	@GetMapping("/{userId}/address")
	public List<UserAddress> getUserAddress(@PathVariable Long userId) {
		return userAddressRepo.findByUserId(userId);
	}
	
	@PostMapping("/{userId}/address/")
	public UserAddress createUserAddress(@PathVariable Long userId,
														 @RequestBody UserAddress userAddressDetails) {
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isPresent()) {			
			userAddressDetails.setUser(userOptional.get());
			return userAddressRepo.save(userAddressDetails);
		} else {
			throw new ResourceNotFoundException("User not found with id " + userId);
		}
	}
	
	@PutMapping("/{userId}/address/{addressId}")
	public ResponseEntity<UserAddress> updateUserAddress(@PathVariable Long userId,
														@PathVariable Long addressId,
														@RequestBody UserAddress userAddressDetails) {
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isPresent()) {
			Optional<UserAddress> userAddressOptional = userAddressRepo.findById(addressId);
			if (userAddressOptional.isPresent()) {
				UserAddress address = userAddressOptional.get();
				address.setAddressLine1(userAddressDetails.getAddressLine1());
				address.setAddressLine2(userAddressDetails.getAddressLine2());
				address.setAddressNumber(userAddressDetails.getAddressNumber());
				address.setPhoneNumber(userAddressDetails.getPhoneNumber());
				address.setPostalCode(userAddressDetails.getPostalCode());
				address.setCity(userAddressDetails.getCity());
				address.setCountry(userAddressDetails.getCountry());
				address.setState(userAddressDetails.getState());
				address.setUser(userAddressDetails.getUser());
				return ResponseEntity.ok(userAddressRepo.save(address));
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{userId}/address/{addressId}")
	public ResponseEntity<Void> deleteUserAddress(@PathVariable Long userId, @PathVariable Long addressId) {
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isPresent()) {
			Optional<UserAddress> address = userAddressRepo.findById(addressId);
			if (address.isPresent()) {
				userAddressRepo.delete(address.get());
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
