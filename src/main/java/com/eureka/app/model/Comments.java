package com.eureka.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comments" )
public class Comments {
	@Id
	private String id;
	@DBRef
	private String ideaId;
	private String commentText;
	
	public Comments() {
		super();
	}
	
	public Comments(String id, String commentText, User commentedBy) {
		super();
		this.id = id;
		this.commentText = commentText;
//		this.commentedBy = commentedBy;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
//	public User getCommentedBy() {
//		return commentedBy;
//	}
//	public void setCommentedBy(User commentedBy) {
//		this.commentedBy = commentedBy;
//	}
	
	
}
