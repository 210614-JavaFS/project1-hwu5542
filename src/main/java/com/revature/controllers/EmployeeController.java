package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class EmployeeController {
	private static EmployeeService employeeService = new EmployeeService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Employee user = getJsonEmployee(request);
		
		if (employeeService.addEmployee(user)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee user = getJsonEmployee(request);
		
		if (employeeService.employeeLogin(user)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public Employee getJsonEmployee(HttpServletRequest request) throws IOException {
		
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		return objectMapper.readValue(body, Employee.class);
	}
}
