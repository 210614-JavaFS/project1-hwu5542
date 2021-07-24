package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	public List<Reimbursement> findAll();
	public Reimbursement findReimbursement(int reimb_id);
	public boolean addReimbursement(Reimbursement reimb);
}
