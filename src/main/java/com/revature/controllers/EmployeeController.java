package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class EmployeeController {
	private static EmployeeService employeeService = new EmployeeService();
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public void getEmployeeProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		Employee user = employeeService.getEmployee(session.getAttribute("ers_username").toString());
		
		if (user != null) {
			String json = objectMapper.writeValueAsString(user);
			response.getWriter().print(json);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void setEmployeeProfile(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		Employee user = getJsonEmployee(request);
		if (user == null) {
			response.setStatus(406);
			return;
		}
		
		user.setErs_username(session.getAttribute("ers_username").toString());
		
		if (employeeService.setEmployeeProfile(user)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response){
		
		Employee user = getJsonEmployee(request);
		
		if (employeeService.addEmployee(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("ers_username", user.getErs_username());
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void userLogin(HttpServletRequest request, HttpServletResponse response){
		Employee user = getJsonEmployee(request);
		
		if (employeeService.employeeLogin(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("ers_username", user.getErs_username());
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void userLogout(HttpServletRequest request, HttpServletResponse response){
		request.getSession().invalidate();
		response.setStatus(404);
	}
	
	public Employee getJsonEmployee(HttpServletRequest request) {
		String body;
		
		try {
			BufferedReader reader = request.getReader();
		
			StringBuilder stringBuilder = new StringBuilder();
		
			String line = reader.readLine();
		
			while (line != null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}
		
			body = new String(stringBuilder);
			
			return objectMapper.readValue(body, Employee.class);

		} catch(IOException e) {
			System.err.println("Readding Request Fail" + e.getMessage());
		}

		return null;
	}
}
