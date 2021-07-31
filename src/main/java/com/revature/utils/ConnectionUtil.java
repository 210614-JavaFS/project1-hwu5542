package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	protected static Connection establishConnection() {
		String url = "jdbc:postgresql://hwu5542-db.cxoqspxuezyw.us-east-2.rds.amazonaws.com:5432/ers";
		String user = "postgres";
		String password = "password";
		
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url, user, password);	
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException q) {
			System.out.println("Can't establish connection: "+q.getMessage());
		}
		return null;
	}
	
	protected static boolean updateDB(String command) {
		try {
			Connection conn = ConnectionUtil.establishConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(command);
		} catch (SQLException e) {
			System.out.println("UPDATE Query Fail: " + e.getMessage());
			return false;
		}		
		return true;
	}
	
	protected static boolean insertDB(String command) {
		return updateDB(command);
	}
	
	protected static ResultSet selectDB(String command) {
		try {
			Connection conn = ConnectionUtil.establishConnection();
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(command);
		} catch (SQLException e) {
			System.out.println("SELECT Query Fail: " + e.getMessage());
		}		
		return null;
	}
}
