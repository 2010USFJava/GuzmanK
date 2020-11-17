package com.revature.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Customer;
import com.revature.beans.JointAccount;

public class JointAccountDAO {
	
	 public static List<JointAccount> fetchJointAccounts(){
	        
		 List<JointAccount> accounts = new ArrayList<JointAccount>();
	        Connection con = DBConnection.connect();
	        String sql = "SELECT * FROM joint_account";
	       
	        Statement stmt = null;
	        
	        try {
	            stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            while(rs.next()){
	                JointAccount account = new JointAccount(rs.getInt(4));
	                account.setId(rs.getLong(1));
	                account.setName(rs.getString(2));
	                account.setBalance(rs.getInt(3));
	                
	                List<Customer> customers = CustomerDAO.fetchCustomersWithJointAccount(account.getId());
	                account.setCustomers(customers);
	                accounts.add(account);
	            }
	            
	            DBConnection.close();
	            return accounts;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return accounts;
	    }

	    public static List<JointAccount> fetchJointAccountsByCustomerID(long id){
	        
	    	List<JointAccount> accounts = new ArrayList<JointAccount>();
	        Connection con = DBConnection.connect();
	        String sql = "SELECT ja.* FROM joint_account ja, joint_acc_customers jac WHERE jac.account_id = ja.account_id AND customer_id = ?";
	       
	        PreparedStatement stmt = null;
	        
	        try {
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, id);
	            ResultSet rs = stmt.executeQuery();
	            
	            while(rs.next()){
	                JointAccount account = new JointAccount(rs.getInt(4));
	                account.setId(rs.getLong(1));
	                account.setName(rs.getString(2));
	                account.setBalance(rs.getInt(3));
	                account.setCustomers(CustomerDAO.fetchCustomersWithJointAccount(account.getId()));
	                accounts.add(account);
	            }
	            
	            DBConnection.close();
	            return accounts;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return accounts;
	    }

	    public static JointAccount addJointAccount(JointAccount account) {
	        Connection con = DBConnection.connect();
	        String sql = "INSERT INTO joint_account(name, balance, status) VALUES(?,?,?,?)";
	        
	        PreparedStatement stmt = null;
	       
	        try {
	            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, account.getName());
	            stmt.setInt(2, account.getBalance());
	            stmt.setInt(3, account.getAccountStatus());
	            
	            long id = stmt.executeUpdate();

	            ResultSet rs = stmt.getGeneratedKeys();
	            if (rs.next()) {
	                id = rs.getLong(1);
	            }

	            account.setId(id);
	            DBConnection.close();
	            return account;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    public static Account approveAccount(Account account) {
	        Connection con = DBConnection.connect();
	        String sql = "UPDATE simple_account SET status = 1 WHERE id = ?";
	        
	        PreparedStatement stmt = null;
	        
	        try {
	            stmt = con.prepareStatement(sql);
	            stmt.setLong(1, account.getId());
	            stmt.executeUpdate();
	            account.setAccountStatus(1);
	            DBConnection.close();
	            return account;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

}
