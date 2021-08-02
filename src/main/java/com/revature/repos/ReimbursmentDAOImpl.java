package com.revature.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.ReimbType;
import com.revature.models.Reimbursment;
import com.revature.utils.ConnectionUtil;

public class ReimbursmentDAOImpl extends ConnectionUtil implements ReimbursmentDAO {

	private Logger log = LoggerFactory.getLogger(ReimbursmentDAOImpl.class);

	public List<ReimbType> getReimbType() {
		ArrayList<ReimbType> reimbT = new ArrayList<ReimbType>();
		ResultSet typeRS = selectDB("SELECT * FROM ERS_REIMBURSMENT_TYPE");
		try {
			while (typeRS.next()) {
				reimbT.add(new ReimbType(typeRS.getInt(1), typeRS.getString(2)));
			}
			return reimbT;
		} catch (SQLException e) {
			System.err.println("Select From Database Fail" + e.getMessage());
		}
		return null;
	}
	
	public List<Reimbursment> getAllReimbursment() {
		return getReimbursmentList("");
	}
	
	public List<Reimbursment> getPendingReimbursment() {
		return getReimbursmentList(", ERS_REIMBURSMENT_STATUS WHERE ERS_REIMBURSMENT.REIMB_STATUS_ID=ERS_REIMBURSMENT_STATUS.REIMB_STATUS_ID AND REIMB_STATUS = 'pending'");
	}
	
	public List<Reimbursment> getReimbursment(String ers_username, boolean pending_flag) {
		String subCommand = "";
		try {
			ResultSet userID = selectDB("SELECT ERS_USERS_ID FROM ERS_USERS WHERE ERS_USERNAME = '" + ers_username + "'");
			userID.next();
			subCommand = ", ERS_REIMBURSMENT_STATUS WHERE ERS_REIMBURSMENT.REIMB_STATUS_ID=ERS_REIMBURSMENT_STATUS.REIMB_STATUS_ID AND REIMB_AUTHOR = " + userID.getInt(1);
		} catch (SQLException e) {
			System.err.println("Select From Database Fail" + e.getMessage());
		}
		if (pending_flag) return getReimbursmentList(subCommand + " AND REIMB_STATUS = 'pending'");
		return getReimbursmentList(subCommand +  " AND REIMB_STATUS != 'pending'");
	}
	
	private List<Reimbursment> getReimbursmentList(String subCommand) {
		
		ArrayList<Reimbursment> reimb = new ArrayList<Reimbursment>();

		String[] command = {"SELECT * FROM ERS_REIMBURSMENT",
							"SELECT ERS_USERNAME FROM ERS_USERS WHERE ERS_USERS_ID = ",
							"SELECT REIMB_STATUS FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS_ID = ",
							"SELECT REIMB_TYPE FROM ERS_REIMBURSMENT_TYPE WHERE REIMB_TYPE_ID = "};
		ResultSet reimbSet[] = new ResultSet[5];
		
		if (!subCommand.equals("")) {
			command[0] += subCommand;
		}
		
		command[0] += " ORDER BY REIMB_SUBMITTED";
		
		try {
			reimbSet[0] = selectDB(command[0]);

			while (reimbSet[0].next()) {
				reimbSet[1] = selectDB(command[1] + reimbSet[0].getInt(7));
				reimbSet[2] = selectDB(command[1] + (reimbSet[0].getInt(8) == 0? 1: reimbSet[0].getInt(8)));
				reimbSet[3] = selectDB(command[2] + reimbSet[0].getInt(9));
				reimbSet[4] = selectDB(command[3] + reimbSet[0].getInt(10));
				
				for (int i=1; i<5; i++) {
					reimbSet[i].next();
				}

				reimb.add(new Reimbursment(reimbSet[0].getInt(1), reimbSet[0].getInt(2), reimbSet[0].getString(3),
						  reimbSet[0].getString(4),	reimbSet[0].getString(5), reimbSet[0].getBoolean(6),
						  reimbSet[0].getInt(7), reimbSet[0].getInt(8),	reimbSet[0].getInt(9), reimbSet[0].getInt(10),
						  reimbSet[1].getString(1), reimbSet[2].getString(1), reimbSet[3].getString(1), reimbSet[4].getString(1)
						  ));
				
			}
		} catch (SQLException e) {
			System.out.println("SELECT Query Fail: " + e.getMessage() + e.getCause());
		}
		
		return reimb;
	}
	 
	public boolean setReimbursment(String ers_username, int reimb_id, String status) {
		try {
			ResultSet resolverRS = selectDB("SELECT ERS_USERS_ID FROM ERS_USERS WHERE ERS_USERNAME = '" + ers_username + "'");
			ResultSet statusRS = selectDB("SELECT REIMB_STATUS_ID FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS = '" + status + "'");
			ResultSet pendingRS = selectDB("SELECT REIMB_STATUS_ID FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS = 'pending'");
			resolverRS.next();
			statusRS.next();
			pendingRS.next();
			String command = "UPDATE ERS_REIMBURSMENT SET REIMB_RESOLVER = " + resolverRS.getInt(1) + ", REIMB_STATUS_ID = " + statusRS.getInt(1) + ", REIMB_RESOLVED = CURRENT_TIMESTAMP WHERE REIMB_ID = " + reimb_id + " AND REIMB_STATUS_ID = " + pendingRS.getString(1);
			updateDB(command);
			log.info("User " + ers_username + " " + status + " a request by ID " + reimb_id + " .");
			return true;
		} catch (SQLException e) {
			System.out.println("SELECT Query Fail: " + e.getMessage());
		} 
		return false;
	}
	
	public boolean setAllReimbursment(String ers_username, String status) {
		try {
			ResultSet resolverRS = selectDB("SELECT ERS_USERS_ID FROM ERS_USERS WHERE ERS_USERNAME = '" + ers_username + "'");
			ResultSet statusRS = selectDB("SELECT REIMB_STATUS_ID FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS = '" + status + "'");
			ResultSet pendingRS = selectDB("SELECT REIMB_STATUS_ID FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS = 'pending'");
			resolverRS.next();
			statusRS.next();
			pendingRS.next();
			String command = "UPDATE ERS_REIMBURSMENT SET REIMB_RESOLVER = " + resolverRS.getInt(1) + ", REIMB_STATUS_ID = " + statusRS.getInt(1) + ", REIMB_RESOLVED = CURRENT_TIMESTAMP WHERE REIMB_STATUS_ID = " + pendingRS.getString(1);
			updateDB(command);
			log.info("User " + ers_username + " " + status + " multiple requests.");
			return true;
		} catch (SQLException e) {
			System.out.println("SELECT Query Fail: " + e.getMessage());
		} 
			
		return false;
	}
	
	public boolean addReimbursment(Reimbursment reimb, String ers_username) {

		try {
			ResultSet authorRS = selectDB("SELECT ERS_USERS_ID FROM ERS_USERS WHERE ERS_USERNAME = '" + ers_username + "'");
			ResultSet pendingRS = selectDB("SELECT REIMB_STATUS_ID FROM ERS_REIMBURSMENT_STATUS WHERE REIMB_STATUS = 'pending'");
			authorRS.next();
			pendingRS.next();
			reimb.setReimb_author(authorRS.getInt(1));
			reimb.setReimb_status_id(pendingRS.getInt(1));
		} catch (SQLException e) {
			System.err.println("Select from database failure." + e.getMessage());
		}
		
		String command = "INSERT INTO ERS_REIMBURSMENT(REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_RECEIPT, REIMB_AUTHOR, "
				+ "REIMB_STATUS_ID, REIMB_TYPE_ID) VALUES ("
				+ reimb.getReimb_amount() + ", '" + reimb.getReimb_description() + "', " + reimb.getReimb_receipt() + ", "
				+ reimb.getReimb_author() + ", " + reimb.getReimb_status_id() + ", " + reimb.getReimb_type_id() + ")";
		if (insertDB(command)) {
			log.info("User " + ers_username + " made a new request for amount " + reimb.getReimb_amount()/100 +".");
			return true;	
		} else {
			return false;
		}
	}
}