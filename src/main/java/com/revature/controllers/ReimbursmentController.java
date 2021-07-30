package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursment;
import com.revature.services.ReimbursmentService;

public class ReimbursmentController {
	private static ReimbursmentService reimbursmentService = new ReimbursmentService();
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public void summitRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Reimbursment reimb = getJsonReimbursment(request);
		
		if (reimbursmentService.addReimbursment(reimb)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	

	
	public Reimbursment getJsonReimbursment(HttpServletRequest request) throws IOException {
		
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		return objectMapper.readValue(body, Reimbursment.class);
	}
}
