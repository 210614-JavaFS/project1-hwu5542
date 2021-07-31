package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursment;
import com.revature.services.ReimbursmentService;

public class ReimbursmentController {
	private static ReimbursmentService reimbursmentService = new ReimbursmentService();
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public void submitRequest(HttpServletRequest request, HttpServletResponse response) {
		
		Reimbursment reimb = getJsonReimbursment(request);
		
		if (reimbursmentService.addReimbursment(reimb)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void pendingRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		List<Reimbursment> reimb = reimbursmentService.getPendingRequest(session.getAttribute("ers_username").toString());
		
		if (reimb != null) {
			setJsonReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void pastRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		List<Reimbursment> reimb = reimbursmentService.getPastRequest(session.getAttribute("ers_username").toString());
		
		if (reimb != null) {
			setJsonReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void setJsonReimbursment(List<Reimbursment> reimb, HttpServletResponse response) {
		try {
			String json = objectMapper.writeValueAsString(reimb);
			response.getWriter().print(json);
		} catch (IOException e) {
			System.err.println("Writing Response Fail" + e.getMessage());
		}
	}
	
	public Reimbursment getJsonReimbursment(HttpServletRequest request) {

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
			
			return objectMapper.readValue(body, Reimbursment.class);

		} catch(IOException e) {
			System.err.println("Readding Request Fail" + e.getMessage());
		}

		return null;
	}
}
