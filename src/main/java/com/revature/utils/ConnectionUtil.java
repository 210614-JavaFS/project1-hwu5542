package com.revature.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionUtil {

	private static String url = "";
	private static String user = "";
	private static String password = "";
	private static Connection conn = null;

	protected static Connection establishConnection() {
		String url = "";
		String user = "";
		String password = "";

		try {
			Scanner loginDBInfo = new Scanner(new File("src//main//resources//login.txt"));
			url = loginDBInfo.nextLine();
			user = loginDBInfo.nextLine();
			password = loginDBInfo.nextLine();
			ConnectionUtil.conn = DriverManager.getConnection(url, user, password);			
		} catch(IOException e) {
			System.out.println("Can't find Login File: "+e.getMessage());
		} catch(SQLException q) {
			System.out.println("Can't establish connection: "+q.getMessage());
		}
		
		return conn;
	}
}
