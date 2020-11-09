package com.revature.beans;

public class Employee extends User {
	
	private String jobTitle;
	
	// Constructor for Employee
	public Employee (String name, String username, String password, String jobTitle) {
		super (name, username, password);
		this.jobTitle = jobTitle;
		
	}

	// Getters and setters
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	

}
