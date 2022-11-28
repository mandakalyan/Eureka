package com.eureka.app.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.eureka.app.model.Department;
 
public class SignupRequest {
	@NotBlank
	@Size(max=15)
	private String fname;
	
	@NotBlank
	@Size(max=20)
	private String lname;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
	private Set<Department> departments;

	private Set<String> roles;

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
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

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRole(Set<String> roles) {
      this.roles = roles;
    }
}
