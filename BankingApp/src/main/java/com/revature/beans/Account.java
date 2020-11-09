package com.revature.beans;

public class Account {
	
	// instance variables
	// 0 means pending, 1 means approved, -1 means rejected
	private int accountStatus;
	private int balance;
	private String name;
	
	// Constructor with fields
	public Account (int accountStatus) {
		this.accountStatus = accountStatus;
		this.balance = 0;
	}

	// Getter and setters
	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Account [accountStatus=" + accountStatus + ", balance=" + balance + ", name=" + name + "]";
	}
	
	

}
