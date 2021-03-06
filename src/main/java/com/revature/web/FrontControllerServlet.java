package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.FinanceManagerController;
import com.revature.controllers.ReimbursmentController;

public class FrontControllerServlet extends HttpServlet {
	EmployeeController employeeController = new EmployeeController();
	ReimbursmentController reimbursmentController = new ReimbursmentController();
	FinanceManagerController financeManagerController = new FinanceManagerController();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("application/json");
		
		response.setStatus(404); //overwrite success default to page not found
		
		final String URL = request.getRequestURI().replace("/project1/", "");
		
		String[] urlSections = URL.split("/");

		if (request.getMethod().equals("POST")) {

			switch(urlSections[0]) {
			case "signUp":
				employeeController.addUser(request, response);
				break;

			case "login":
				if (urlSections.length == 1) {
					employeeController.userLogin(request, response);
				} else {
					employeeController.userAutoLogin(request, response);
				}
				break;
		
			case "profile":
				if (urlSections.length == 1) {
					employeeController.getEmployeeProfile(request, response);
				} else {
					employeeController.setEmployeeProfile(request, response);
				}
			break;
		
			case "signOut":
				employeeController.userLogout(request, response);
			break;
		
			case "employee":
				if (urlSections[1].equals("submit")) {
					if (urlSections.length > 2) {
						reimbursmentController.getReimbType(request, response);						
					} else {
						reimbursmentController.submitRequest(request, response);
					}
				} else if (urlSections[1].equals("pending")) {
					reimbursmentController.pendingRequest(request, response);
				} else if (urlSections[1].equals("past")) {
					reimbursmentController.pastRequest(request, response);
				}
			break;

			case "manager":
				if (urlSections[1].equals("all")) {
					financeManagerController.allRequest(request, response);
				} else if (urlSections[1].equals("approve")) {
					if (urlSections[2].equals("single")) {
						financeManagerController.approveSingleRequest(request, response, urlSections[3]);
						
					} else if (urlSections[2].equals("all")) {
						financeManagerController.approveAllRequest(request, response);						
					}
				} else if (urlSections[1].equals("deny")) {
					if (urlSections[2].equals("single")) {
						financeManagerController.denySingleRequest(request, response, urlSections[3]);
						
					} else if (urlSections[2].equals("all")) {
						financeManagerController.denyAllRequest(request, response);						
					}
				} else if (urlSections[1].equals("pending")) {
					financeManagerController.pendingRequest(request, response);
				}
				break;
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
