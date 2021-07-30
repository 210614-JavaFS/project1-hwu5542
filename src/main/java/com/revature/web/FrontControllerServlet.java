package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.ReimbursmentController;

public class FrontControllerServlet extends HttpServlet {
	EmployeeController employeeController = new EmployeeController();
	ReimbursmentController reimbursmentController = new ReimbursmentController();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("application/json");
		
		response.setStatus(404); //overwrite success default to page not found
		
		final String URL = request.getRequestURI().replace("/project1/", "");
		
		System.out.println(URL);
		
		String[] urlSections = URL.split("/");
		
		switch(urlSections[0]) {
		case "signUp":
			if (request.getMethod().equals("POST")) {
				employeeController.addUser(request, response);
			}
			break;

		case "login":
			if (request.getMethod().equals("POST")) {
				employeeController.userLogin(request, response);
			}
			break;
		
		case "profile":
			if (urlSections.length == 1) {
				if (request.getMethod().equals("POST")) {
					employeeController.getEmployeeProfile(request, response);
				}
			} else {
				if (request.getMethod().equals("POST")) {
					employeeController.setEmployeeProfile(request, response);
				}
			}
			break;
		
		case "logout":
			if (request.getMethod().equals("POST")) {
				employeeController.userLogout(request, response);
			}
			break;
		
		case "reimb":
			if (urlSections.length == 1) {
				
			} else {
				if (urlSections[1].equals("summit")) {
					reimbursmentController.summitRequest(request, response);
				}
			}
			break;
		case "employee":
			break;
		case "manager":
			break;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
