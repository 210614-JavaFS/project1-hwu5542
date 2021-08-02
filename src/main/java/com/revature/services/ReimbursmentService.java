package com.revature.services;

import java.util.List;

import com.revature.models.ReimbType;
import com.revature.models.Reimbursment;
import com.revature.repos.ReimbursmentDAO;
import com.revature.repos.ReimbursmentDAOImpl;

public class ReimbursmentService {
	ReimbursmentDAO reimbursmentDAO = new ReimbursmentDAOImpl();
	
	public List<ReimbType> getReimbType() {
		return reimbursmentDAO.getReimbType();
	}
	
	public boolean addReimbursment(Reimbursment reimb) {
		return reimbursmentDAO.addReimbursment(reimb);
	}

	public List<Reimbursment> getPendingRequest(String ers_username) {
		return reimbursmentDAO.getReimbursment(ers_username, true);
	}
	
	public List<Reimbursment> getPastRequest(String ers_username) {
		return reimbursmentDAO.getReimbursment(ers_username, false);
	}
	
	public List<Reimbursment> getAllRequest() {
		return reimbursmentDAO.getAllReimbursment();
	}
	
	public List<Reimbursment> getPendingRequest() {
		return reimbursmentDAO.getPendingReimbursment();
	}
	
	public boolean setPendingRequest(String ers_username, int reimb_id, String status) {
		if (reimb_id == 0) return reimbursmentDAO.setAllReimbursment(ers_username, status);
		return reimbursmentDAO.setReimbursment(ers_username, reimb_id, status);
	}
	
}
