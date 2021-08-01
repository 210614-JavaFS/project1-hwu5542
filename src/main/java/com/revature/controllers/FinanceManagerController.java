package com.revature.controllers;

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
	
	public void approveAllRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();
		String status = session.getAttribute("reimb_status").toString();

		if (reimbursmentService.setPendingRequest(ers_username, 0, status)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void approveSingleRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();
		String status = session.getAttribute("reimb_status").toString();
		Reimbursment targetReimb = getJsonReimbursment(request);
		if (reimbursmentService.setPendingRequest(ers_username, targetReimb.getReimb_id(), status)) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
}
