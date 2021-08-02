package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursment;

public interface ReimbursmentDAO {
	public List<Reimbursment> getAllReimbursment();
	public List<Reimbursment> getPendingReimbursment();
	public List<Reimbursment> getReimbursment(String ers_username, boolean pending_flag);
	public boolean addReimbursment(Reimbursment reimb);
	public boolean setReimbursment(String ers_username, int reimb_id, String status);
	public boolean setAllReimbursment(String ers_username, String status);
}