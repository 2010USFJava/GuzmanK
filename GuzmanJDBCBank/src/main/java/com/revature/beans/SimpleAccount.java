package com.revature.beans;

public class SimpleAccount extends Account {
	
	private Customer customer;
    private Long customerId;
    public SimpleAccount(int accountStatus) {
        super(accountStatus);

        super.setName("Simple Account");
    }


    // getters and setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString(){

        String str = "Balance: " + this.getBalance() + "\n" + "*** Customer Details ***\n"+customer.toString() + "\nAccount Status: ";
        if(this.getAccountStatus() == -1){
            str += "Denied";
        }
        else if(this.getAccountStatus() == 0){
            str += "Pending Approval";
        }
        else{
            str += "Approved";
        }
        str += "\n";
        return  str;

    }

}
