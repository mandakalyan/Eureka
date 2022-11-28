package com.eureka.app.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Idea")
public class Idea {
	@Id
	private String id;
	private String fname;
	private String lname;
	private String ideaTitle;
	private String ideaDescription;
	private String createdDate;
	private int likesCount;
	private int commentsCount;
	private int rewards;
	private List<Comments> comments = new ArrayList<>();
	private IStatus ideaStatus;
	
	public Idea() {
		super();
	}

	public Idea(String id, String fname, String lname, String ideaTitle, String ideaDescription, String createdBy,
			String createdDate, int likesCount, int commentsCount, int rewards, List<Likes> likes,
			List<Comments> comments, IStatus ideaStatus) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.ideaTitle = ideaTitle;
		this.ideaDescription = ideaDescription;
		this.createdDate = createdDate;
		this.likesCount = likesCount;
		this.commentsCount = commentsCount;
		this.rewards = rewards;
//		this.likes = likes;
		this.comments = comments;
		this.ideaStatus = ideaStatus;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

//	public List<Likes> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(List<Likes> likes) {
//		this.likes = likes;
//	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getIdeaDescription() {
		return ideaDescription;
	}
	public void setIdeaDescription(String ideaDescription) {
		this.ideaDescription = ideaDescription;
	}

	public IStatus getIdeaStatus() {
		return ideaStatus;
	}

	public void setIdeaStatus(IStatus ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

	public String getCreationDate() {
		return createdDate;
	}

	public void setCreationDate(String creationDate) {
		this.createdDate = creationDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}	
	
}
