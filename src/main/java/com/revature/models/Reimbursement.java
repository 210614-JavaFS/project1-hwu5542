package com.revature.models;

public class Reimbursement {

	private class ERS_Reimbursement {
		int reimb_id;
		int reimb_amount;
		String reimb_submitted;
		String reimb_resolved;
		String reimb_description;
		boolean reimb_receipt;
		int reimb_author;
		int reimb_resolver;
		int reimb_status_id;
		int reimb_type_id;
		String reimb_status;
		String reimb_type;
		
		public ERS_Reimbursement(int reimb_id, int reimb_amount, String reimb_submitted, String reimb_resolved,
				String reimb_description, boolean reimb_receipt, int reimb_author, int reimb_resolver,
				int reimb_status_id, int reimb_type_id, String reimb_status, String reimb_type) {
			super();
			this.reimb_id = reimb_id;
			this.reimb_amount = reimb_amount;
			this.reimb_submitted = reimb_submitted;
			this.reimb_resolved = reimb_resolved;
			this.reimb_description = reimb_description;
			this.reimb_receipt = reimb_receipt;
			this.reimb_author = reimb_author;
			this.reimb_resolver = reimb_resolver;
			this.reimb_status_id = reimb_status_id;
			this.reimb_type_id = reimb_type_id;
			this.reimb_status = reimb_status;
			this.reimb_type = reimb_type;
		}
		
	}
	
	private ERS_Reimbursement reimb_row;
	
	public int getReimb_id() {
		return reimb_row.reimb_id;
	}
	
	public void setReimb_id(int reimb_id) {
		reimb_row.reimb_id = reimb_id;
	}
	
	public int getReimb_amount() {
		return reimb_row.reimb_amount;
	}
	
	public void setReimb_amount(int reimb_amount) {
		reimb_row.reimb_amount = reimb_amount;
	}
	
	public String getReimb_submitted() {
		return reimb_row.reimb_submitted;
	}
	
	public void setReimb_submitted(String reimb_submitted) {
		reimb_row.reimb_submitted = reimb_submitted;
	}
	
	public String getReimb_resolved() {
		return reimb_row.reimb_resolved;
	}
	
	public void setReimb_resolved(String reimb_resolved) {
		reimb_row.reimb_resolved = reimb_resolved;
	}
	
	public String getReimb_description() {
		return reimb_row.reimb_description;
	}
	
	public void setReimb_description(String reimb_description) {
		reimb_row.reimb_description = reimb_description;
	}
	
	public boolean getReimb_receipt() {
		return reimb_row.reimb_receipt;
	}
	
	public void setReimb_receipt(boolean reimb_receipt) {
		reimb_row.reimb_receipt = reimb_receipt;
	}
	
	public int getReimb_author() {
		return reimb_row.reimb_author;
	}
	
	public void setReimb_author(int reimb_author) {
		reimb_row.reimb_author = reimb_author;
	}
	
	public int getReimb_resolver() {
		return reimb_row.reimb_resolver;
	}
	
	public void setReimb_resolver(int reimb_resolver) {
		reimb_row.reimb_resolver = reimb_resolver;
	}
	
	public int getReimb_status_id() {
		return reimb_row.reimb_status_id;
	}
	
	public void setReimb_status_id(int reimb_status_id) {
		reimb_row.reimb_status_id = reimb_status_id;
	}
	
	public int getReimb_type_id() {
		return reimb_row.reimb_type_id;
	}
	
	public void setReimb_type_id(int reimb_type_id) {
		reimb_row.reimb_type_id = reimb_type_id;
	}
	
	public String getReimb_status() {
		return reimb_row.reimb_status;
	}
	
	public void setReimb_status(String reimb_status) {
		reimb_row.reimb_status = reimb_status;
	}
	
	public String getReimb_type() {
		return reimb_row.reimb_type;
	}
	
	public void setReimb_type(String reimb_type) {
		reimb_row.reimb_type = reimb_type;
	}

	public Reimbursement(int reimb_id, int reimb_amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, boolean reimb_receipt, int reimb_author, int reimb_resolver,
			int reimb_status_id, int reimb_type_id, String reimb_status, String reimb_type) {
		
		reimb_row = new ERS_Reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_resolved,
				reimb_description, reimb_receipt, reimb_author, reimb_resolver,
				reimb_status_id, reimb_type_id, reimb_status, reimb_type);
	}
}
