
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.db.EmployeeDAO;
import com.revature.beans.BankAdmin;
import com.revature.beans.Customer;
import com.revature.beans.Customer;
import com.revature.beans.Employee;
import com.revature.beans.JointAccount;
import com.revature.beans.SimpleAccount;

public class Driver {
	
	// main method
	public static void main(String args[]) {


        // Creating a bank admin
        // Bank admin is hardcoded and cannot be created again
        BankAdmin admin = new BankAdmin("Bank Admin Name", "AdminUserName", "12345678", "Bank Admin");
        // Creating a bank object for performing all operations

        Bank bank = new Bank(new ArrayList<Customer>(), admin, new ArrayList<JointAccount>());
        BankAdmin loggedInAdmin = null;
        Customer loggedInCustomer = null;
        Employee loggedInEmployee = null;

        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;
        // This loop keeps running until the user chooses to logout
        while(keepRunning) {

            // Displaying the main menu
            System.out.println("1. Register an Employee");
            System.out.println("2. Register a Customer");
            System.out.println("3. Login Employee");
            System.out.println("4. Login Customer");
            System.out.println("5. Login Bank Admin");
            System.out.println("6. Exit Program");

            // Getting user input
            System.out.print("\tInput Here: ");
            String choice = input.nextLine();


            if(choice.equals("1")) {
                // User chose to register employee
                bank.registerEmployee();
                
            } else if(choice.equals("2")) {
                // User chose to register customer
                bank.registerCustomer();
                
            } else if(choice.equals("3")) {
                // User chose to login employee
                loggedInEmployee = bank.loginEmployee();
                
                if(loggedInEmployee == null) {
                    System.out.println("Could not sign in. Please try again.");
                
                } else {
                    // Employee logged in successfully. Displaying menu to employee
                    System.out.println("Employee Logged In Successfully.");

                    while(loggedInEmployee != null) {
                        // Displaying the employee a sub menu
                        System.out.println("*********** Employee Sub Menu **********");
                        System.out.println("1. See all accounts that are pending approval");
                        System.out.println("2. Approve an account");
                        System.out.println("3. Deny an account");
                        System.out.println("4. Search and display customer data");
                        System.out.println("5. Log out");

                        System.out.print("\tInput Here: ");
                        String menu2choice = input.nextLine();

                        if(menu2choice.equals("1")) {
                            bank.seePendingApproval();
                       
                        } else if(menu2choice.equals("2")) {
                            bank.setApproved();
                        
                        } else if(menu2choice.equals("3")) {
                            bank.setDenied();
                        
                        } else if(menu2choice.equals("4")) {
                            bank.displayCustomerData();
                       
                        } else if(menu2choice.equals("5")) {
                            loggedInEmployee = null;
                            System.out.println("Employee Logged out successfully");
                       
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }


                    }




                }
            } else if(choice.equals("4")) {
                // User chose to login customer
                loggedInCustomer = bank.loginCustomer();
                
                if(loggedInCustomer == null) {
                    System.out.println("Could not sign in. Please try again.");
                
                } else {
                    // Customer logged in successfully. Displaying menu to employee
                    System.out.println("Customer Logged In Successfully.");

                    while(loggedInCustomer != null) {
                        // Displaying another menu to the customer
                        System.out.println("*********** Customer Menu ************");

                        System.out.println("1. Apply for a simple account");
                        System.out.println("2. Apply for a joint account");
                        System.out.println("3. View Balance");
                        System.out.println("4. Make Withdrawal");
                        System.out.println("5. Make Deposit");
                        System.out.println("6. Log Out");
                        System.out.print("\tInput Here: ");
                        String menu2Choice = input.nextLine();
                        if (menu2Choice.equals("1")) {
                            bank.applyForSimpleAccount(loggedInCustomer);
                        } else if (menu2Choice.equals("2")) {
                            bank.applyForJointAccount(loggedInCustomer);
                        } else if (menu2Choice.equals("3")) {
                            bank.viewBalance(loggedInCustomer);
                        } else if (menu2Choice.equals("4")) {
                            bank.withdraw(loggedInCustomer);
                        } else if (menu2Choice.equals("5")) {
                            bank.deposit(loggedInCustomer);
                        }
                        else if (menu2Choice.equals("6")) {
                            loggedInCustomer = null;
                            System.out.println("********** Customer Logged Out Successfully **********");
                        }
                        else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }

                }
            } else if(choice.equals("5")) {
                // User chose to login back admin
                loggedInAdmin = bank.loginAdmin();
                if(loggedInAdmin == null){
                    System.out.println("Could not sign in. Please try again.");
                
                } else {
                    // Admin logged in successfully. Displaying menu to employee
                    System.out.println("Bank Admin Logged In Successfully.");

                    // Displaying another menu to the admin
                    while(loggedInAdmin != null) {
                        //Displaying another menu to the customer
                        System.out.println("*********** Adminn Menu ************");
                        System.out.println("1. View Balance");
                        System.out.println("2. Make Withdrawal");
                        System.out.println("3. Make Deposit");
                        System.out.println("4. See all accounts that are pending approval");
                        System.out.println("5. Approve an account");
                        System.out.println("6. Deny an account");
                        System.out.println("7. Search and display customer data");
                        System.out.println("8. Log Out");
                        System.out.print("\tInput Here: ");
                        String menu2Choice = input.nextLine();
                       
                        if (menu2Choice.equals("1")) {
                            System.out.println("Input username: ");
                            String uname = input.nextLine();
                            Customer customer = bank.searchCustomer(uname);
                            
                            if(customer == null) {
                                System.out.println("Username does not exist");
                            
                            } else {
                                bank.viewBalance(customer);
                            }
                        
                        } else if (menu2Choice.equals("2")) {
                            System.out.println("Input username: ");
                            String uname = input.nextLine();
                            Customer customer = bank.searchCustomer(uname);
                            
                            if(customer == null) {
                                System.out.println("Username does not exist");
                            
                            } else {
                                bank.withdraw(customer);
                            }
                        
                        } else if (menu2Choice.equals("3")) {
                            System.out.println("Input username: ");
                            String uname = input.nextLine();
                            Customer customer = bank.searchCustomer(uname);
                            
                            if(customer == null) {
                                System.out.println("Username does not exist");
                            
                            } else {
                                bank.deposit(customer);
                            }
                        
                        } else if(menu2Choice.equals("4")) {
                            bank.seePendingApproval();
                        
                        } else if(menu2Choice.equals("5")) {
                            bank.setApproved();
                        
                        } else if(menu2Choice.equals("6")) {
                            bank.setDenied();
                        
                        } else if(menu2Choice.equals("7")) {
                            bank.displayCustomerData();
                       
                        } else if (menu2Choice.equals("8")) {
                            loggedInAdmin = null;
                            System.out.println("********** Admin Logged Out Successfully **********");
                       
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }


                }
            } else if(choice.equals("6")) {
                //User chose to exit the program
                keepRunning = false;
            
            } else{
                //User inputted an invalid value
                System.out.println("Invalid Input. Please try again.");
            }

        }



    }

}
