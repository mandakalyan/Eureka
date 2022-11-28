package com.eureka.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.app.model.User;
import com.eureka.app.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/users")
	public List<User> getAllUsers(@RequestParam(required = false) String email) {

		if (email != null) {
			List<User> users = new ArrayList<>();
			users.add(userRepo.findByEmail(email).get());
			return users;
		}
		return userRepo.findAll();
	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable String id) {
		
		return userRepo.findById(id);
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepo.save(user);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id,
			@RequestBody User user) {
		
		Optional<User> myUser = userRepo.findById(id);
		
		if (!myUser.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		myUser.get().setFname(user.getFname());
		myUser.get().setLname(user.getLname());
		myUser.get().setEmail(user.getEmail());
		myUser.get().setPassword(user.getPassword());
		myUser.get().setDepartments(user.getDepartments());
		myUser.get().setRoles(user.getRoles());
		myUser.get().setActiveStatus(user.isActive());
		myUser.get().setRewards(user.getRewards());
		return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
	}

	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@DeleteMapping("/users/{id}")
//	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) {
//		userRepo.deleteById(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
	
	@GetMapping("/users/isActive")
	public List<User> getAllActiveUsers(){
		return userRepo.findByIsActive(true);
	}
	
//	@GetMapping("users/userExists/{email}")
//	public boolean userExists(@PathVariable String email) {
//		return userRepo.existsByEmail(email);
//	}
	
	@GetMapping("users/emailExists/{email}")
	public boolean emailExists(@PathVariable String email) {
		return userRepo.existsByEmail(email);
	}
}
