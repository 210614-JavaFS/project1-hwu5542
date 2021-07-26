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
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Employee user = objectMapper.readValue(body, Employee.class);
		
		if (employeeService.addEmployee(user)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
}
