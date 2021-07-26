package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repos.EmployeeDAO;
import com.revature.repos.EmployeeDAOImpl;

public class EmployeeService {
	private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	
	public List<Employee> getAllEmployee() {
		return employeeDAO.findAll();
	}
	
	public Employee getEmployee(int username) {
		return employeeDAO.findEmployee(username);
	}
	
	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}
}
