package com.revature.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
	
	 private static String username = "kiarakg";
	    
	    private static String password = "mypassword";
	    private static String url = "jdbc:postgresql://java2010usf.cmijjv1lc3ok.us-east-2.rds.amazonaws.com:5432/postgres";
	    private static Connection connection = null;

	    private static Logger logger = Logger.getLogger(String.valueOf(DBConnection.class));

	    public static Connection connect() {
	       
	    	try {
	            Class.forName("org.postgresql.Driver");
	            if (connection == null || connection.isClosed())
	                connection = DriverManager.getConnection(url, username, password);
	       
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
	        
	        return connection;
	    }

	    public static void close() {
	        
	    	try {
	            connection.close();
	            
	        } catch (SQLException e) {
	            logger.log(Level.SEVERE, e.getClass().getName() + ": " + e.getMessage());
	        }
	    }


}
