package com.revature.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

public class EmployeeDAOImpl extends ConnectionUtil implements EmployeeDAO {
	
	public List<Employee> findAll() {
		return getEmployeeList("");
	}
	
	public Employee findEmployee(String ers_username) {
		return getEmployeeList(ers_username).get(0);
	}
	
	private List<Employee> getEmployeeList(String ers_username) {
		
		ArrayList<Employee> users = new ArrayList<Employee>();

		String[] command = {"SELECT * FROM ERS_USERS",
							"SELECT USER_ROLE FROM ERS_USER_ROLES WHERE ERS_USER_ROLE_ID = "};
		if (ers_username != "") {
			command[0] += " WHERE ERS_USERNAME = '" + ers_username + "'";
		}

		ResultSet usersSet[] = new ResultSet[2];
		usersSet[0] = selectDB(command[0]);

		try {
			while (usersSet[0].next()) {
				usersSet[1] =  selectDB(command[1] + usersSet[0].getInt(7));
				usersSet[1].next();
				users.add(new Employee(usersSet[0].getInt(1), usersSet[0].getString(2), usersSet[0].getString(3),
							(usersSet[0].getString(4) == null?"N/A":usersSet[0].getString(4)), (usersSet[0].getString(5) == null?"N/A":usersSet[0].getString(5)),
							(usersSet[0].getString(6) == null?"N/A":usersSet[0].getString(6)), usersSet[0].getInt(7), usersSet[1].getString(1)));
			}
		} catch (SQLException e) {
			System.err.println("Access Result Set Fail: " + e.getMessage());
		}

		return users;
	}
	

	public boolean addEmployee(Employee users) {
		String command = "INSERT INTO ERS_USERS(ers_username, ers_password) VALUES ('"
					   + users.getErs_username() + "', '" + users.getErs_password() + "')";
		return insertDB(command);
	}
	
	public String employeeLogin(Employee users) {
		String command = "SELECT ers_password FROM ERS_USERS WHERE ers_username = '" + users.getErs_username() + "'";
		ResultSet userPswd= selectDB(command);
		try {
			if (userPswd.next()) {
				return userPswd.getString(1);
			} else {
				System.out.println("User not found.");
			}
		} catch(SQLException e) {
			System.err.println("Access Result Set Fail: " + e.getMessage());
		}
		return null;			
	}


	public boolean addEmployeeProfile(Employee users) {
		String command = "UPDATE ERS_USERS SET ers_password = '" + users.getErs_password() + "', user_first_name = '"
		               + users.getUser_first_name() + "', user_last_name = '" + users.getUser_last_name() + "', user_email = '"
					   + users.getUser_email() + "' WHERE ers_username = '" + users.getErs_username() + "'";
		System.out.println(command);
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
}