package com.revature.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAOImpl extends ConnectionUtil implements ReimbursementDAO {


	public List<Reimbursement> findAll() {
		return selectDB(0);
	}
	
	public Reimbursement findReimbursement(int reimb_id) {
		return selectDB(reimb_id).get(0);
	}
	
	private List<Reimbursement> selectDB(int reimb_id) {
		
		ArrayList<Reimbursement> reimb = new ArrayList<Reimbursement>();

		String[] command = {"SELECT * FROM ERS_REIMBURSEMENT",
							"SELECT REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE REIMB_STATUS_ID = ",
							"SELECT REIMB_TYPE FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = "};
		ResultSet reimbSet[] = new ResultSet[3];
		
		if (reimb_id > 0) {
			command[0] += " WHERE REIMB_ID = " + reimb_id;
		}
		
		try {
			Connection conn = ConnectionUtil.establishConnection();
			Statement stmt = conn.createStatement();
			reimbSet[0] = stmt.executeQuery(command[0]);
			while (reimbSet[0].next()) {
				reimbSet[1] =  stmt.executeQuery(command[1] + reimbSet[0].getInt(9));
				reimbSet[2] =  stmt.executeQuery(command[2] + reimbSet[0].getInt(10));
				reimb.add(new Reimbursement(reimbSet[0].getInt(1), reimbSet[0].getInt(2), reimbSet[0].getString(3),
						  reimbSet[0].getString(4),	reimbSet[0].getString(5), reimbSet[0].getBoolean(6),
						  reimbSet[0].getInt(7), reimbSet[0].getInt(8),	reimbSet[0].getInt(9), reimbSet[0].getInt(10),
						  reimbSet[1].getString(1), reimbSet[2].getString(1)));
			}
		} catch (SQLException e) {
			System.out.println("SELECT Query Fail: " + e.getMessage());
		}
		
		return reimb;
	}
	
	public boolean addReimbursement(Reimbursement reimb) {
		String command = "INSERT INTO ERS_REIMBURSEMENT(REIMB_AMMOUNT, REIMB_DESCRIPTION, REIMB_RECEIPT, REIMB_AUTHOR, "
				+ "REIMB_RESULVER, REIMB_STATUS_ID, REIMB_TYPE_ID) VALUES ("
				+ reimb.getReimb_amount() + ", " + reimb.getReimb_description() + ", " + reimb.getReimb_receipt() + ", "
				+ reimb.getReimb_author() + ", " + reimb.getReimb_resolver() + ", " + reimb.getReimb_status_id() + ", "
				+ reimb.getReimb_type_id() + ")";
		try {
			Connection conn = ConnectionUtil.establishConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(command);
		} catch (SQLException e) {
			System.out.println("INSERT Query Fail: " + e.getMessage());
			return false;
		}
		
		return true;
	}
}