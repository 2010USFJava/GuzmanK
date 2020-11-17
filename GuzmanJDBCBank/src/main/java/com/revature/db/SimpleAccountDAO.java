package com.revature.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.SimpleAccount;

public class SimpleAccountDAO {
	
	public static List<SimpleAccount> fetchSimpleAccounts() {
        
		List<SimpleAccount> accounts = new ArrayList<SimpleAccount>();
        Connection con = DBConnection.connect();
        String sql = "SELECT * FROM simple_account";
        
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
           
            while(rs.next()){
                SimpleAccount account = new SimpleAccount(rs.getInt(4));
                account.setId(rs.getLong(1));
                account.setName(rs.getString(2));
                account.setBalance(rs.getInt(3));
                account.setCustomer(CustomerDAO.getCustomerByID(rs.getLong(5)));
                accounts.add(account);
            }
            
            DBConnection.close();
            return accounts;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return accounts;
    }

    public static List<SimpleAccount> fetchSimpleAccountsByCustomerID(long id){
        
    	List<SimpleAccount> accounts = new ArrayList<SimpleAccount>();
        Connection con = DBConnection.connect();
        String sql = "SELECT * FROM simple_account WHERE customer_id = ?";
        
        PreparedStatement stmt = null;
       
        try {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                SimpleAccount account = new SimpleAccount(rs.getInt(4));
                account.setId(rs.getLong(1));
                account.setName(rs.getString(2));
                account.setBalance(rs.getInt(3));
                accounts.add(account);
            }
            
            DBConnection.close();
            return accounts;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return accounts;
    }

    public static SimpleAccount addSimpleAccount(SimpleAccount account) {
        Connection con = DBConnection.connect();
        String sql = "INSERT INTO simple_account(name, balance, status, customer_id) VALUES(?,?,?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, account.getName());
            stmt.setInt(2, account.getBalance());
            stmt.setInt(3, account.getAccountStatus());
            stmt.setLong(4, account.getCustomer().getId());
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
