package com.revature.services;

import java.util.List;

import com.revature.controllers.PasswordController;
import com.revature.models.Employee;
import com.revature.repos.EmployeeDAO;
import com.revature.repos.EmployeeDAOImpl;

public class EmployeeService {
	private static EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private static PasswordController passwordController = new PasswordController();
	
	public List<Employee> getAllEmployee() {
		return employeeDAO.findAll();
	}
	
	public Employee getEmployee(int username) {
		return employeeDAO.findEmployee(username);
	}
	
	public boolean addEmployee(Employee employee) {
		employee.setErs_password(passwordController.passwordHash(employee.getErs_password()));
		return employeeDAO.addEmployee(employee);
	}
	
	public boolean employeeLogin(Employee employee) {
		String password = employeeDAO.employeeLogin(employee);
		return passwordController.validatePassword(employee.getErs_password(), password);
	}
}
