package com.revature.repos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	public Employee findEmployee(int users_id);
	public boolean addEmployee(Employee users);
}
