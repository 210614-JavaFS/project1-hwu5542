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
	
	public Employee getEmployee(String ers_username) {
		return employeeDAO.findEmployee(ers_username);
	}
	
	public boolean addEmployee(Employee employee) {
		employee.setErs_password(passwordController.passwordHash(employee.getErs_password()));
		if (employee.getErs_password().length()>200) {
			System.out.println("Password Too Long");
			return false;
		}
		return employeeDAO.addEmployee(employee);
	}
	
	public boolean setEmployeeProfile(Employee employee) {
		employee.setErs_password(passwordController.passwordHash(employee.getErs_password()));
		if (employee.getErs_password().length()>200) {
			System.out.println("Password Too Long");
			return false;
		}
		return employeeDAO.addEmployeeProfile(employee);
	}
	
	public Employee employeeLogin(Employee employee) {
		String password = employeeDAO.employeeLogin(employee);
		if (passwordController.validatePassword(employee.getErs_password(), password))
			return (employeeDAO.findEmployee(employee.getErs_username()));
		return null;
	}
}
