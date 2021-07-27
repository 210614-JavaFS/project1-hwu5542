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
		return getEmployeeList(0);
	}
	
	public Employee findEmployee(int users_id) {
		return getEmployeeList(users_id).get(0);
	}
	
	private List<Employee> getEmployeeList(int users_id) {
		
		ArrayList<Employee> users = new ArrayList<Employee>();

		String[] command = {"SELECT * FROM ERS_USERS",
							"SELECT USER_ROLE FROM ERS_USER_ROLES WHERE ERS_USER_ROLE_ID = "};
		if (users_id > 0) {
			command[0] += " WHERE ERS_USERS_ID = " + users_id;
		}

		ResultSet usersSet[] = new ResultSet[2];
		usersSet[0] = selectDB(command[0]);

		try {
			while (usersSet[0].next()) {
				usersSet[1] =  selectDB(command[1] + usersSet[0].getInt(7));
				users.add(new Employee(usersSet[0].getInt(1), usersSet[0].getString(2), usersSet[0].getString(3),
							usersSet[0].getString(4), usersSet[0].getString(5), usersSet[0].getString(6),
							usersSet[0].getInt(7), usersSet[1].getString(1)));
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
	
	/*
	public boolean addEmployee(Employee users) {
		String command = "INSERT INTO ERS_USERS(ers_username, ers_password, user_first_name, "
					   + "user_last_name, user_email, user_role_id) VALUES ("
					   + users.getErs_username() + ", " + users.getErs_password() + ", "
					   + users.getUser_first_name() + ", " + users.getUser_last_name() + ", "
					   + users.getUser_email() + ", " + users.getUser_role_id() + ")";
		try {
			Connection conn = ConnectionUtil.establishConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(command);
		} catch (SQLException e) {
			System.out.println("INSERT Query Fail: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	*/
}