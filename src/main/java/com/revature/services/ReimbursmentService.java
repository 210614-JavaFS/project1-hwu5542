package com.revature.services;

import com.revature.models.Reimbursment;
import com.revature.repos.ReimbursmentDAO;
import com.revature.repos.ReimbursmentDAOImpl;

public class ReimbursmentService {
	ReimbursmentDAO reimbursmentDAO = new ReimbursmentDAOImpl();
	
	public boolean addReimbursment(Reimbursment reimb) {
		return reimbursmentDAO.addReimbursment(reimb);
	}
}
