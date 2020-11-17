package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

public class JointAccount extends Account {
	
	private List<Customer> customers;

    public JointAccount(int accountStatus) {
        super(accountStatus);
        this.customers = new ArrayList<Customer>();
        super.setName("Joint Account");

    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString(){

        String str ="Balance: " + this.getBalance() + "\n";
        str += "AccountStatus: ";
        if(this.getAccountStatus() == -1){
            str += "Denied";
        
        } else if(this.getAccountStatus() == 0){
            str += "Pending Approval";
        
        } else{
            str += "Approved";
        }
        str+= "\n";
        str += "*** Customers Detail ***\n";

        for(Customer c: this.getCustomers()){
            str += c.toString();
        }



        return str;

    }

}
