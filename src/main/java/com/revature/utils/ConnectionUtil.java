package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
