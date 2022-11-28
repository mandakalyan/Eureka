package com.eureka.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eureka.app.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
	List<User> findByIsActive(boolean isActive);
	Boolean existsByEmail(String email);
}
