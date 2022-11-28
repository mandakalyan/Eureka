package com.eureka.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eureka.app.model.Idea;

public interface IdeasRepository extends MongoRepository<Idea, String> {
	List<Idea> findByFname(String fname);
	List<Idea> findByIdeaTitle(String ideaTitle);
}
