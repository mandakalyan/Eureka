package com.eureka.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BusinessChallenges")
public class BusinessChallenges {
	@Id
	private String id;
	private String fname;
	private String lname;
	private String challengeTitle;
	private String challengeDescription;
	private String expiryDate;
	private BStatus challengeStatus;
	
	public BusinessChallenges() {
		super();
	}
	
	

	public BusinessChallenges(String id, String fname, String lname, String challengeTitle, String challengeDescription,
			String expiryDate, BStatus challengeStatus) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.challengeTitle = challengeTitle;
		this.challengeDescription = challengeDescription;
		this.expiryDate = expiryDate;
		this.challengeStatus = challengeStatus;
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

	public String getChallengeTitle() {
		return challengeTitle;
	}

	public void setChallengeTitle(String challengeTitle) {
		this.challengeTitle = challengeTitle;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getChallengeDescription() {
		return challengeDescription;
	}
	public void setChallengeDescription(String challengeDescription) {
		this.challengeDescription = challengeDescription;
	}



	public BStatus getChallengeStatus() {
		return challengeStatus;
	}



	public void setChallengeStatus(BStatus challengeStatus) {
		this.challengeStatus = challengeStatus;
	}
	
	
	
}
