package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursment;

public interface ReimbursmentDAO {
	public List<Reimbursment> findAll();
	public Reimbursment findReimbursment(int reimb_id);
	public boolean addReimbursment(Reimbursment reimb);
}
