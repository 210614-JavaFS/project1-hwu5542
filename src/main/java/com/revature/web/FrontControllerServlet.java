package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.EmployeeController;

public class FrontControllerServlet extends HttpServlet {
	EmployeeController employeeController = new EmployeeController();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("application/json");
		
		response.setStatus(404); //overwrite success default to page not found
		
		final String URL = request.getRequestURI().replace("/project1/", "");
		
		System.out.println(URL);
		
		String[] urlSections = URL.split("/");
		
		switch(urlSections[0]) {
		case "signUp":
			if (urlSections.length == 1) {
				if (request.getMethod().equals("POST")) {
					employeeController.addUser(request, response);
				}
			}
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
