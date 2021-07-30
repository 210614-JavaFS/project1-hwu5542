package com.revature.repos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	public Employee findEmployee(String ers_username);
	public boolean addEmployee(Employee users);
	public String employeeLogin(Employee users);
	public boolean addEmployeeProfile(Employee users);
}
