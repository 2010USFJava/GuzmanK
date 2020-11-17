package com.revature.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;

public class EmployeeDAO {
	
	public static List<Employee> fetchEmployees() {
        
		List<Employee> employees = new ArrayList<Employee>();
        Connection con = DBConnection.connect();
        
        String sql = "SELECT * FROM employees";
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Employee employee = new Employee(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                employee.setId(rs.getLong(1));
                employees.add(employee);
            }
            DBConnection.close();
            return employees;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
	public static Employee addEmployee(Employee employee) {
        Connection con = DBConnection.connect();
        String sql = "INSERT INTO employees(name, username, password, job_title) VALUES(?,?,?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getUsername());
            stmt.setString(3, employee.getPassword());
            stmt.setString(4, employee.getJobTitle());
            long id = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }

            employee.setId(id);
            DBConnection.close();
            return employee;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Employee login(String username, String password){
        Connection con = DBConnection.connect();
        String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
        
        PreparedStatement stmt = null;
        Employee employee = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new Employee(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                employee.setId(rs.getLong(1));
            }
            
            DBConnection.close();
            return employee;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
