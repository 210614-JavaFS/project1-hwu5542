package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimbursment;
import com.revature.services.ReimbursmentService;

public class FinanceManagerController extends ReimbursmentController {
	private static ReimbursmentService reimbursmentService = new ReimbursmentService();
	
	public void allRequest(HttpServletRequest request, HttpServletResponse response) {
		List<Reimbursment> reimb = reimbursmentService.getAllRequest();
		
		if (reimb != null) {
			setJsonReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void pendingRequest(HttpServletRequest request, HttpServletResponse response) {
		List<Reimbursment> reimb = reimbursmentService.getPendingRequest();
		
		if (reimb != null) {
			setJsonReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void approveAllRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();

		if (reimbursmentService.setPendingRequest(ers_username, 0, "approved")) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void denyAllRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();
		
		if (reimbursmentService.setPendingRequest(ers_username, 0, "denied")) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void approveSingleRequest(HttpServletRequest request, HttpServletResponse response, String reimb_id) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();
		
		if (reimbursmentService.setPendingRequest(ers_username, Integer.parseInt(reimb_id), "approved")) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}

	public void denySingleRequest(HttpServletRequest request, HttpServletResponse response, String reimb_id) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();

		if (reimbursmentService.setPendingRequest(ers_username, Integer.parseInt(reimb_id), "denied")) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
}
