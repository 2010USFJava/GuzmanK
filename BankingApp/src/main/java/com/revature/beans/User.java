package com.revature.beans;

public class User {
	
	// instance variables
	private String name;
	private String username;
	private String password;
	
	// Constructor
	public User (String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals (Object obj) {
		
		if (obj == this) {
			return true;
			
		// We can compare a string with a user
		} else if (obj instanceof String) {
			String str = (String) obj;
			return this.username.equals(str);
			
		} else if (! (obj instanceof User)) {
			return false;
			
		// We can compare two users
		} else {
			User u2 = (User) obj;
			return this.username.equals(u2.username);
		}	
		
	}


}
