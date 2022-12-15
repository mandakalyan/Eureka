package com.eureka.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Likes" )
public class Likes {
	@Id
	private String id;
	private String  userId;
	private String ideaId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(String ideaId) {
		this.ideaId = ideaId;
	}
	@Override
	public String toString() {
		return "Likes [id=" + id + ", userId=" + userId + ", ideaId=" + ideaId + "]";
	}
	public Likes(String id, String userId, String ideaId) {
		super();
		this.id = id;
		this.userId = userId;
		this.ideaId = ideaId;
	}
	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
