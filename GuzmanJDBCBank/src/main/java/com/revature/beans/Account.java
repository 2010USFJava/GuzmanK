package com.revature.beans;

public abstract class Account {
	
	private Long id;
    private int accountStatus;  // 0 means pending approval, 1 means approved, -1 means rejected

    private int balance;
    private String name;

    public Account(int accountStatus) {
        this.accountStatus = accountStatus;
        this.balance = 0;

    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

}
