package com.revature.beans;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Bank {
	
	private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
    private BankAdmin bankAdmin;
    private ArrayList<SimpleAccount> simpleAccounts;
    private ArrayList<JointAccount> jointAccounts;

    Logger logger = Logger.getLogger(String.valueOf(Bank.class));


    // The constructor for bank
    public Bank(ArrayList<Customer> customers, ArrayList<Employee> employees, BankAdmin bankAdmin, ArrayList<SimpleAccount> accounts, ArrayList<JointAccount> accounts2) {
        this.customers = customers;
        this.employees = employees;
        this.bankAdmin = bankAdmin;
        this.simpleAccounts = accounts;
        this.jointAccounts = accounts2;

        logger.info("Bank Object Created");
    }

    // The readEmployee method is a private  helper method for getting input for employee data
    private Employee readEmployee() {

        // Getting input for each employee attribute
        Scanner input = new Scanner(System.in);
        
        System.out.println("Input Name of Employee: ");
        String name = input.nextLine();
        
        System.out.println("Input Employee Job Title: ");
        String jobTitle = input.nextLine();
        
        System.out.println("Input Employee username: ");
        String userName = input.nextLine();

        System.out.println("Input Employee Password: ");
        String password = input.nextLine();
        
        while(password.length() < 6) {
            System.out.println("*** The minimum length for password is 6 characters. Please try again. ***");
            System.out.println("Input Employee Password: ");
            password = input.nextLine();
        }


        Employee emp = new Employee(name, userName, password, jobTitle);

        // Making sure that the employee does not exist already. The employees are compared based on username
        if (employees.contains(emp)) {
            logger.info("Creating Employee Failed. Username already exists.");
            return null;
        }

        logger.info("New Employee Created");

        return emp;

    }

    // The readCustomer method is a private  helper method for getting input for customer data
    private Customer readCustomer() {

        // Getting input for each customer attribute
        Scanner input = new Scanner(System.in);
        System.out.println("Input Name of Customer: ");
        
        String name = input.nextLine();
        System.out.println("Input Customer Bio: ");
        
        String bio = input.nextLine();
        System.out.println("Is the customer married? (Input y or n): ");
        
        String married = input.nextLine();

        while (!married.equals("Y") && !married.equals("y") && !married.equals("N") && !married.equals("n")) {
            System.out.println("Invalid Input! Please try again.");
            System.out.println("Is the customer married? (Input y or n): ");
            married = input.nextLine();
        }

        boolean marriedBool = false;
        if (married.equals("Y") || married.equals("y")) {
            marriedBool = true;
        }


        System.out.println("Input Customer username: ");
        String userName = input.nextLine();

        System.out.println("Input Customer Password: ");
        String password = input.nextLine();
        
        while(password.length() < 6) {
            System.out.println("*** The minimum length for password is 6 characters. Please try again. ***");
            System.out.println("Input Employee Password: ");
            password = input.nextLine();
        }



        Customer customer = new Customer(name, userName, password, bio, marriedBool);

        // Making sure that the employee does not exist already. The employees are compared based on username
        if(customers.contains(customer)) {
            logger.info("Customer Creation Failed. Username already exists");
            return null;
        }

        logger.info("New Customer Created");

        return customer;

    }

    // The registerEmployee method gets info and adds employee to the bank
    public void registerEmployee() {
        Employee emp = readEmployee();
        
        if (emp!= null) {
            this.employees.add(emp);
            System.out.println("Employee added successfully.");
            
        } else {
            System.out.println("Error Adding the Employee. Please make sure that the username is unique.");
        }
    }

    // The registerCustomer method gets info and adds customer to the bank
    public void registerCustomer() {
        Customer customer = readCustomer();
        if (customer!= null){
            this.customers.add(customer);
            System.out.println("Customer added successfully.");
            
        } else {
            System.out.println("Error Adding the Customer. Please make sure that the username is unique.");
        }
    }

    // The loginEmployee method gets input for username and password and tries to login to the system
    public Employee loginEmployee() {


        // Getting input from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Input Employee Username: ");
        
        String username = input.nextLine();
        System.out.println("Input Employee password");
        
        String password = input.nextLine();


        // Finding the employee from the list
        for(Employee e: employees) {
            if(e.getUsername().equals(username) && e.getPassword().equals(password)) {
                logger.info("Employee Logged In");
                return e;

            }
        }
        logger.info("Login failed (Invalid credentials)");
        return null;

    }

    // The loginCustomer method gets input for username and password and tries to login to the system
    public Customer loginCustomer() {


        // Getting input from the user
        Scanner input = new Scanner(System.in);
        
        System.out.println("Input Customer Username: ");
        String username = input.nextLine();
        
        System.out.println("Input Customer password");
        String password = input.nextLine();

        // Finding the employee from the list
        for(Customer c: customers) {
            if(c.getUsername().equals(username) && c.getPassword().equals(password)) {
                logger.info("Customer Logged In");
                return c;
            }
        }

        logger.info("Login Failed (Invalid credentials)");
        return null;

    }

    // The loginAdmin method gets input for username and password and tries to login to the system
    public BankAdmin loginAdmin() {


        // Getting input from the user
        Scanner input = new Scanner(System.in);
        
        System.out.println("Input Admin Username: ");
        String username = input.nextLine();
        
        System.out.println("Input Admin password");
        String password = input.nextLine();


        if (bankAdmin.getUsername().equals(username) && bankAdmin.getPassword().equals(password)) {
            logger.info("Admin Logged In");
            return bankAdmin;
        }
        
        logger.info("Login failed (Invalid credentials)");
        return null;

    }

    // The applyForSimpleAccount method applies for a simple account
    public void applyForSimpleAccount(Customer customer) {

        SimpleAccount sa = new SimpleAccount(0);
        sa.setCustomer(customer);
        customer.getAccounts().add(sa);
        this.simpleAccounts.add(sa);
        
        logger.info("Account application forwarded");
        System.out.println("Successfully applied for account");

    }

    // The applyForJointAccount method applies for a joint account
    public void applyForJointAccount(Customer customer) {

        JointAccount sa = new JointAccount(0);
        sa.getCustomers().add(customer);
        customer.getJointAccounts().add(sa);
        this.jointAccounts.add(sa);

        logger.info("Joint account application forwarded");
        System.out.println("Successfully applied for joint account");

    }

    // The withdraw method is used to withdraw from an account
    public void withdraw(Customer customer) {

        ArrayList <SimpleAccount> SA = new ArrayList <SimpleAccount>();
        ArrayList <JointAccount> JA = new ArrayList <JointAccount>();

        for(SimpleAccount s: customer.getAccounts()) {
            if(s.getAccountStatus() == 1) {
                SA.add(s);
            }
        }

        for(JointAccount s: customer.getJointAccounts()) {
            if(s.getAccountStatus() == 1) {
                JA.add(s);
            }
        }

        if(SA.size() == 0 && JA.size() == 0) {
            System.out.println("Error withdrawing. You don't have any approved account");
            
            logger.info("Withdraw failed (No approved account)");
            
        } else {

            // Printing simple accounts
            if(SA.size() != 0) {
                System.out.println("You have following simple accounts: ");
                for(int i =  0; i <SA.size(); i++) {
                    System.out.println(i + " Balance = " + SA.get(i).getBalance());
                }
            }


            // Printing joint accounts
            if(JA.size() != 0) {
                System.out.println("You have following joint accounts: ");
                for(int i = 0; i < JA.size(); i++) {
                    System.out.println(i + " Balance = " + JA.get(i).getBalance());
                    System.out.println("There are following members in this account: ");
                    for(Customer c: JA.get(i).getCustomers()) {
                        System.out.println(c.getUsername());
                    }
                }
            }
            
            Scanner input = new Scanner(System.in);

            System.out.println("Do you want to withdraw from a simple account or a joint account? Input S or J : ");
            String ac = input.nextLine();

            while(!ac.equals("S") && !ac.equals("s") && !ac.equals("J") && !ac.equals("j")){
                System.out.println("Invalid Value. Please try again.");

                System.out.println("Do you want to withdraw from a simple account or a joint account? Input S or J : ");
                ac = input.nextLine();
            }

            System.out.println("Input the account number: ");
            int accountNumber = input.nextInt();


            System.out.println("Input the amount to withdraw: ");
            int val = input.nextInt();

            while(val <= 0) {
                System.out.println("Invalid values. Value must be positive. Please try again");
                System.out.println("Input the amount to withdraw: ");
                val = input.nextInt();
            }

            input.nextLine();   // Ignoring end of line character
           
            if(ac.equals("S") || ac.equals("s")) {
                if(accountNumber < SA.size() && accountNumber >= 0) {
                    if(val <= SA.get(accountNumber).getBalance()) {
                    	
                        // Removing the amount
                        SA.get(accountNumber).setBalance(SA.get(accountNumber).getBalance()-val);
                        System.out.println("Amount withdrawn successfully");
                        
                        logger.info("Withdraw Successful");
                        
                    } else {
                        System.out.println("Insufficient Balance. Please try again.");
                        logger.info("Withdraw failed. Insufficient Balance");
                    }
                    
                } else {
                    System.out.println("Invalid account number. Please try again.");
                    logger.info("Withdraw failed. Invalid number for account");
                }
                
            } else {
            	
                if(accountNumber < JA.size()) {
                    if(val <= JA.get(accountNumber).getBalance()) {
                    	
                        // Removing the amount
                        JA.get(accountNumber).setBalance(JA.get(accountNumber).getBalance()-val);
                        System.out.println("Amount withdrawn successfully"); 
                        
                        logger.info("Withdraw Successful");
                        
                    } else {
                        System.out.println("Insufficient Balance. Please try again.");
                        logger.info("Withdraw failed. Insufficient balance");
                    }
                    
                } else {
                    System.out.println("Invalid account number. Please try again.");
                    logger.info("Withdraw failed. Incorrect Account Number");
                }
            }

        }

    }


    // This method searches and returns customer by username
    public Customer searchCustomer(String userName) {

        for(Customer c: this.customers){
            if(c.getUsername().equals(userName)) {
                return c;
            }
        }
        return null;

    }


    // This method searches for customer and displays customer data
    public void displayCustomerData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the username of the customer: ");

        String userName = input.nextLine();

        Customer c = searchCustomer(userName);

        if(c == null) {
            System.out.println("A user with this user name does not exist");
            
        } else {
            System.out.println(c);
            System.out.println("Simple accounts owned by user");
            
            for(SimpleAccount a: c.getAccounts()) {
                System.out.println(a);
                System.out.println("");
            }

            System.out.println("Joint accounts owned by user");
            
            for(JointAccount a: c.getJointAccounts()) {
                System.out.println(a);
                System.out.println("");
            }

        }


    }


    // The viewBalance method prints the accounts and balance of the accounts of a customer
    public void viewBalance(Customer customer) {
        ArrayList <SimpleAccount> SA = new ArrayList <SimpleAccount>();
        ArrayList <JointAccount> JA = new ArrayList <JointAccount>();

        for (SimpleAccount s : customer.getAccounts()) {
            if (s.getAccountStatus() == 1) {
                SA.add(s);
            }
        }

        for (JointAccount s : customer.getJointAccounts()) {
            if (s.getAccountStatus() == 1) {
                JA.add(s);
            }
        }

        if (SA.size() == 0 && JA.size() == 0) {
            System.out.println("Error. You don't have any approved account");
            
        } else {

            // Printing simple accounts
            if (SA.size() != 0) {
                System.out.println("You have following simple accounts: ");
                for (int i = 0; i < SA.size(); i++) {
                    System.out.println(i + " Balance = " + SA.get(i).getBalance());
                }
            }


            // Printing joint accounts
            if (JA.size() != 0) {
                System.out.println("You have following joint accounts: ");
                for (int i = 0; i < JA.size(); i++) {
                	
                    System.out.println(i + " Balance = " + JA.get(i).getBalance());
                    System.out.println("There are following members in this account: ");
                    
                    for (Customer c : JA.get(i).getCustomers()) {
                        System.out.println(c.getUsername());
                    }
                }
            }


        }
    }

    // This method gets all accounts that are pending approval
    public ArrayList <Account> getAllPendingApproval(){
        ArrayList <Account> SA = new ArrayList <Account>();

        for (Account s : this.simpleAccounts) {
            if (s.getAccountStatus() == 0) {
                SA.add(s);
            }
        }

        for (Account s : this.jointAccounts) {
            if (s.getAccountStatus() == 0) {
                SA.add(s);
            }

        }

        return SA;

    }

    // This method shows all accounts that are pending approval
    public void seePendingApproval() {

        ArrayList<Account> SA = getAllPendingApproval();

        if (SA.size() == 0) {
            System.out.println("There are no accounts pending approval");
            
        } else {

            System.out.println("************ These are the accounts pending approval ************");
            int counter = 0;
            for(Account a: SA){
                System.out.println(counter + " Type: " + a.getName());

                if(a instanceof SimpleAccount) {
                    SimpleAccount sa = (SimpleAccount) a;

                    System.out.println(sa);
                    
                } else if(a instanceof  JointAccount) {
                    JointAccount ja = (JointAccount) a;
                    System.out.println(ja);
                }

            }

        }

    }

    // This method sets an account as approved
    void setApproved() {

        ArrayList<Account> SA = getAllPendingApproval();
        seePendingApproval();
        
        System.out.println("Input the number of account from the list above to set as approved: ");
        Scanner input = new Scanner(System.in);
        
        int num = input.nextInt();
        input.nextLine();

        if(SA.size() != 0 && num < SA.size() && num >= 0) {
            SA.get(num).setAccountStatus(1);
            
            System.out.println("Account successfully set as approved");
            logger.info("Account set as approved");
            
        } else {
            System.out.println("Error. Invalid account number");
            
            logger.info("Account not set as approved. Account number does not exist.");
        }



    }

    //This method sets an account as rejected
    void setDenied() {

        ArrayList <Account> SA = getAllPendingApproval();
        seePendingApproval();
        
        System.out.println("Input the number of account from the list above to set as denied: ");
        Scanner input = new Scanner(System.in);
        
        int num = input.nextInt();
        input.nextLine();

        if(SA.size() != 0 && num < SA.size() && num >= 0) {
            SA.get(num).setAccountStatus(-1);
            System.out.println("Account successfully set as denied");
            
            logger.info("Account Successfully Denied");
            
        } else {
            System.out.println("Error. Invalid account number");
            logger.info("Account Deny Failed");
        }



    }


    // The deposit method is used to deposit to an account
    public void deposit(Customer customer) {

        ArrayList <SimpleAccount> SA = new ArrayList <SimpleAccount>();
        ArrayList <JointAccount> JA = new ArrayList <JointAccount>();

        for(SimpleAccount s: customer.getAccounts()) {
            if(s.getAccountStatus() == 1) {
                SA.add(s);
            }
        }

        for(JointAccount s: customer.getJointAccounts()) {
            if(s.getAccountStatus() == 1){
                JA.add(s);
            }
        }

        if(SA.size() == 0 && JA.size() == 0) {
            System.out.println("Error depositing. You don't have any approved account");
            
            logger.info("Deposit Failed. No Approved Account");
            
        } else {

            // Printing simple accounts
            if(SA.size() != 0) {
                System.out.println("You have following simple accounts: ");
                
                for(int i =  0; i <SA.size(); i++) {
                    System.out.println(i + " Balance = " + SA.get(i).getBalance());
                }
            }


            // Printing joint accounts
            if(JA.size() != 0){
                System.out.println("You have following joint accounts: ");
                
                for(int i = 0; i < JA.size(); i++){
                    System.out.println(i + " Balance = " + JA.get(i).getBalance());
                    System.out.println("There are following members in this account: ");
                    
                    for(Customer c: JA.get(i).getCustomers()){
                        System.out.println(c.getUsername());
                    }
                }
            }
            
            Scanner input = new Scanner(System.in);

            System.out.println("Do you want to deposit to a simple account or a joint account? Input S or J : ");
            String ac = input.nextLine();

            while(!ac.equals("S") && !ac.equals("s") && !ac.equals("J") && !ac.equals("j")){
                System.out.println("Invalid Value. Please try again.");

                System.out.println("Do you want to deposit to a simple account or a joint account? Input S or J : ");
                ac = input.nextLine();
            }

            System.out.println("Input the account number from the list above: ");
            int accountNumber = input.nextInt();


            System.out.println("Input the amount to withdraw: ");
            int val = input.nextInt();

            while(val <= 0) {
                System.out.println("Invalid values. Value must be positive. Please try again");
                System.out.println("Input the amount to withdraw: ");
                val = input.nextInt();
            }

            input.nextLine();   // Ignoring end of line character
            if(ac.equals("S") || ac.equals("s")) {
                if(accountNumber < SA.size() && accountNumber>=0) {

                        SA.get(accountNumber).setBalance(SA.get(accountNumber).getBalance()+val);
                        System.out.println("Amount successfully deposited");
                        
                        logger.info("Deposit Successful.");

                } else {
                    System.out.println("Invalid account number. Please try again.");
                    
                    logger.info("Deposit Failed. Account number does not exist");
                }
            } else {
                if(accountNumber < JA.size()) {
                        JA.get(accountNumber).setBalance(JA.get(accountNumber).getBalance()+val);
                        System.out.println("Amount successfully deposited");
                        
                        logger.info("Deposit Successful");

                } else {
                    System.out.println("Invalid account number. Please try again.");
                    
                    logger.info("Deposit Failed. Insufficient Balance");
                }
            }

        }

    }

    // Getters and Setters
    public ArrayList <Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList <Customer> customers) {
        this.customers = customers;
    }

    public ArrayList <Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList <Employee> employees) {
        this.employees = employees;
    }

    public BankAdmin getBankAdmin() {
        return bankAdmin;
    }

    public void setBankAdmin(BankAdmin bankAdmin) {
        this.bankAdmin = bankAdmin;
    }
}




