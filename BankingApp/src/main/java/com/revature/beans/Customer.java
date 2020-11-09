package com.revature.beans;

import java.util.ArrayList;

public class Customer extends User {
	
	private String Bio;
	private ArrayList <SimpleAccount> accounts;
	private ArrayList <JointAccount> jointAccounts;
	private boolean married;
	
	// Constructor for customer
	public Customer (String name, String username, String password, String bio, boolean married) {
		super (name, username, password);
		Bio = bio;
		this.married = married;
		accounts = new ArrayList <SimpleAccount>();
		jointAccounts = new ArrayList <JointAccount>();
	}

	// Getters and setters
	public String getBio() {
		return Bio;
	}

	public void setBio(String bio) {
		Bio = bio;
	}

	public ArrayList<SimpleAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<SimpleAccount> accounts) {
		this.accounts = accounts;
	}

	public ArrayList<JointAccount> getJointAccounts() {
		return jointAccounts;
	}

	public void setJointAccounts(ArrayList<JointAccount> jointAccounts) {
		this.jointAccounts = jointAccounts;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}
	
	@Override
	public String toString() {
		
		String str = "Customer Name: " + this.getName() + "\n";
		str += "Customer User Name: " + this.getUsername() + "\n";
		str += "Customer Bio: " + this.getBio() + "\n";
		str += "Marriage Status: " + this.isMarried() + "\n";
		
		return str;
	}
	
	

}
