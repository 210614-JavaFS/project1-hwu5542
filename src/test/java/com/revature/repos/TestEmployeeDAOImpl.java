package com.revature.repos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Employee;

public class TestEmployeeDAOImpl {
	
	private static Employee user; 

	@BeforeEach
	public void newUser() {
		user = new Employee(0, "hong", "hoho", "hong", "wu", "hwu@mail.com", 0, "");
	}
	
	@Test
	public void testAddEmployee() {
		EmployeeDAO employeeDAO = new EmployeeDAOImpl();
		assertTrue(employeeDAO.addEmployee(user));
	}
}
