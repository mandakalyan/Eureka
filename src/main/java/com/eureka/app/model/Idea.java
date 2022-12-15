package com.eureka.app.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Idea")
public class Idea {
	
	@Transient
    public static final String SEQUENCE_NAME = "ideas_sequence";
	
	@Id
	private String id;
	@Indexed(unique=true)
	private String ideaId;
	private String fname;
	private String lname;
	private String ideaTitle;
	private String ideaDescription;
	private String createdDate;
	private int likesCount;
	private int commentsCount;
	private int rewards;
	private String userId;
	
	@DBRef
	private List<Comments> comments = new ArrayList<>();
	@DBRef
	private List<Likes> likes =new ArrayList<>();
	private IStatus ideaStatus;
	public Idea() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Idea(String id, String ideaId, String fname, String lname, String ideaTitle, String ideaDescription,
			String createdDate, int likesCount, int commentsCount, int rewards, String userId, List<Comments> comments,
			List<Likes> likes, IStatus ideaStatus) {
		super();
		this.id = id;
		this.ideaId = ideaId;
		this.fname = fname;
		this.lname = lname;
		this.ideaTitle = ideaTitle;
		this.ideaDescription = ideaDescription;
		this.createdDate = createdDate;
		this.likesCount = likesCount;
		this.commentsCount = commentsCount;
		this.rewards = rewards;
		this.userId = userId;
		this.comments = comments;
		this.likes = likes;
		this.ideaStatus = ideaStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(String ideaId) {
		this.ideaId = ideaId;
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
	public String getIdeaDescription() {
		return ideaDescription;
	}
	public void setIdeaDescription(String ideaDescription) {
		this.ideaDescription = ideaDescription;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
	public int getRewards() {
		return rewards;
	}
	public void setRewards(int rewards) {
		this.rewards = rewards;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public IStatus getIdeaStatus() {
		return ideaStatus;
	}
	public void setIdeaStatus(IStatus ideaStatus) {
		this.ideaStatus = ideaStatus;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	@Override
	public String toString() {
		return "Idea [id=" + id + ", ideaId=" + ideaId + ", fname=" + fname + ", lname=" + lname + ", ideaTitle="
				+ ideaTitle + ", ideaDescription=" + ideaDescription + ", createdDate=" + createdDate + ", likesCount="
				+ likesCount + ", commentsCount=" + commentsCount + ", rewards=" + rewards + ", userId=" + userId
				+ ", comments=" + comments + ", likes=" + likes + ", ideaStatus=" + ideaStatus + "]";
	}
	
	
}