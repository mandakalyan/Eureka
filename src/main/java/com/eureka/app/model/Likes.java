package com.eureka.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Likes" )
public class Likes {
	@Id
	private String id;
	private User likedBy;
	
	public Likes() {
		super();
	}

	public Likes(String id, int likesCount, User likedBy) {
		super();
		this.id = id;
		this.likedBy = likedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(User likedBy) {
		this.likedBy = likedBy;
	}
	
	
}
