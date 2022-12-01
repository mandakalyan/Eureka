package com.eureka.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	@Id
	private String id;
	@NotBlank
	@Size(max=15)
	private String fname;
	@NotBlank
	@Size(max=20)
	private String lname;
	@NotBlank
	@Indexed(unique=true)
	@Size(max=50)
	@Email
	private String email;
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	@NotNull
	private Set<Department> departments = new HashSet<>();
	
	@NotNull
	@DBRef
	private Set<Role> roles = new HashSet<>();
	private boolean isActive;
	private int rewards;
	@DBRef
	private Set<Idea> favorites = new HashSet<>();
	
	public User() {
		super();
	}

	public User(@NotBlank @Size(max = 15) String fname, @NotBlank @Size(max = 20) String lname,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6, max = 40) String password,
			@NotNull Set<Department> departments) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.departments = departments;
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

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActiveStatus(boolean isActive) {
		this.isActive = isActive;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	public Set<Idea> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Idea> favorites) {
		this.favorites = favorites;
	}
	
}
