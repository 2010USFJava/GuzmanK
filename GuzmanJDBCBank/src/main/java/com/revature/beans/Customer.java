package com.revature.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.List;


public class Customer extends User {
	
	private String Bio;
    private List<SimpleAccount> accounts;
    private List<JointAccount> jointAccounts;
    private boolean married;


    // The constructor for customer
    public Customer(String name, String username, String password, String bio, boolean married) {
        
    	super(name, username, password);
        
    	Bio = bio;
        this.married = married;
        accounts = new ArrayList<>();
        jointAccounts = new ArrayList<>();
    }

    // Getters and Setters

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public List<SimpleAccount> getAccounts() {
        return accounts;
    }

    public void setSimpleAccount(SimpleAccount account){
        account.setCustomer(this);
        accounts.add(account);
    }

    public void setAccounts(List<SimpleAccount> accounts) {
        this.accounts = accounts;
    }

    public List<JointAccount> getJointAccounts() {
        return jointAccounts;
    }

    public void setJointAccounts(List<JointAccount> jointAccounts) {
        this.jointAccounts = jointAccounts;
    }



    @Override
    public String toString(){

        String str = "Customer Name: " + this.getName() + "\n";
        str += "Customer User Name: " + this.getUsername() + "\n";
        str += "Customer Bio: " + this.getBio() + "\n";
        str += "Marriage Status: " + this.isMarried() + "\n";


        return str;


    }

}
