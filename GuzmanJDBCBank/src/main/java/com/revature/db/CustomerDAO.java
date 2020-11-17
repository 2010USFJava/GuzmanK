package com.revature.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Customer;


public class CustomerDAO {
	
	public static List<Customer> fetchCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        Connection con = DBConnection.connect();
        String sql = "SELECT * FROM customers";
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                Customer customer = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                customer.setId(rs.getLong(1));
                customer.setAccounts(SimpleAccountDAO.fetchSimpleAccountsByCustomerID(customer.getId()));
                customers.add(customer);
            }
            
            DBConnection.close();
            return customers;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }

    public static List<Customer> fetchCustomersWithJointAccount(long accountID){
        
    	List<Customer> customers = new ArrayList<Customer>();
        Connection con = DBConnection.connect();
        
        String sql = "SELECT c.* FROM customers c, joint_acc_customers ja WHERE ja.customer_id = c.customer_id AND ja.account_id = ?";
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, accountID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Customer customer = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                customer.setId(rs.getLong(1));
                customer.setAccounts(SimpleAccountDAO.fetchSimpleAccountsByCustomerID(customer.getId()));
                customers.add(customer);
            }
            
            DBConnection.close();
            return customers;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customers;
    }

    public static Customer getCustomerByID(Long id) {
        
    	Customer customer = null;
        Connection con = DBConnection.connect();
        
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()) {
                customer = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                customer.setId(rs.getLong(1));
                customer.setAccounts(SimpleAccountDAO.fetchSimpleAccountsByCustomerID(customer.getId()));
            }
            
            DBConnection.close();
            return customer;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customer;
    }

    public static Customer login(String username, String password) {
        
    	Customer customer = null;
        Connection con = DBConnection.connect();
        
        String sql = "SELECT * FROM customers WHERE username = ? AND password = ?";
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                customer = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                customer.setId(rs.getLong(1));
                customer.setAccounts(SimpleAccountDAO.fetchSimpleAccountsByCustomerID(customer.getId()));
            }
           
            DBConnection.close();
            return customer;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customer;
    }


    public static Customer addCustomer(Customer customer) {
        
    	Connection con = DBConnection.connect();
        String sql = "INSERT INTO customers(name, username, password, bio, married) VALUES(?,?,?,?,?)";
       
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getUsername());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getBio());
            stmt.setBoolean(5, customer.isMarried());
            long id = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }

            customer.setId(id);
            DBConnection.close();
            
            return customer;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
