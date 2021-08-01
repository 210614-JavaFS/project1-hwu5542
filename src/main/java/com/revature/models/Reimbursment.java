package com.revature.models;

public class Reimbursment {
	
	private int reimb_id;
	private int reimb_amount;
	private String reimb_submitted;
	private String reimb_resolved;
	private String reimb_description;
	private boolean reimb_receipt;
	private int reimb_author;
	private int reimb_resolver;
	private int reimb_status_id;
	private int reimb_type_id;
	private String reimb_author_usr;
	private String reimb_resolver_usr;
	private String reimb_status;
	private String reimb_type;
		
	public Reimbursment() {
		super();
	}
	
	public Reimbursment(int reimb_id, int reimb_amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, boolean reimb_receipt, int reimb_author, int reimb_resolver,
			int reimb_status_id, int reimb_type_id, String reimb_author_usr, String reimb_resolver_usr,
			String reimb_status, String reimb_type) {
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
		this.reimb_author_usr = reimb_author_usr;
		this.reimb_resolver_usr = reimb_resolver_usr;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public String getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public String getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public boolean getReimb_receipt() {
		return reimb_receipt;
	}

	public void setReimb_receipt(boolean reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public int getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}

	public int getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public int getReimb_status_id() {
		return reimb_status_id;
	}

	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public int getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
	
	public String getReimb_author_usr() {
		return reimb_author_usr;
	}

	public void setReimb_author_usr(String reimb_author_usr) {
		this.reimb_author_usr = reimb_author_usr;
	}

	public String getReimb_resolver_usr() {
		return reimb_resolver_usr;
	}

	public void setReimb_resolver_usr(String reimb_resolver_usr) {
		this.reimb_resolver_usr = reimb_resolver_usr;
	}

	public String getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}

	public String getReimb_type() {
		return reimb_type;
	}

	public void setReimb_type(String reimb_type) {
		this.reimb_type = reimb_type;
	}
}
