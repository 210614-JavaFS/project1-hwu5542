package com.revature.models;

public class Employee {
	private class ERS_user {
		int ers_users_id;
		String ers_username;
		String ers_password;
		String user_first_name;
		String user_last_name;
		String user_email;
		int user_role_id;
		String user_role;
		
		public ERS_user(int ers_users_id, String ers_username, String ers_password, String user_first_name,
				String user_last_name, String user_email, int user_role_id, String user_role) {
			super();
			this.ers_users_id = ers_users_id;
			this.ers_username = ers_username;
			this.ers_password = ers_password;
			this.user_first_name = user_first_name;
			this.user_last_name = user_last_name;
			this.user_email = user_email;
			this.user_role_id = user_role_id;
			this.user_role = user_role;
		}		
	}

	public int getErs_users_id() {
		return user_row.ers_users_id;
	}
	
	public void setErs_users_id(int ers_users_id) {
		user_row.ers_users_id = ers_users_id;
	}
	
	public String getErs_username() {
		return user_row.ers_username;
	}
	
	public void setErs_username(String ers_username) {
		user_row.ers_username = ers_username;
	}
	
	public String getErs_password() {
		return user_row.ers_password;
	}
	
	public void setErs_password(String ers_password) {
		user_row.ers_password = ers_password;
	}
	
	public String getUser_first_name() {
		return user_row.user_first_name;
	}
	
	public void setUser_first_name(String user_first_name) {
		user_row.user_first_name = user_first_name;
	}
	
	public String getUser_last_name() {
		return user_row.user_last_name;
	}
	
	public void setUser_last_name(String user_last_name) {
		user_row.user_last_name = user_last_name;
	}
	
	public String getUser_email() {
		return user_row.user_email;
	}
	
	public void setUser_email(String user_email) {
		user_row.user_email = user_email;
	}
	
	public int getUser_role_id() {
		return user_row.user_role_id;
	}
	
	public void setUser_role_id(int user_role_id) {
		user_row.user_role_id = user_role_id;
	}
	
	public String getUser_role() {
		return user_row.user_role;
	}
	
	public void setUser_role(String user_role) {
		user_row.user_role = user_role;
	}	

	private ERS_user user_row;
	
	public Employee(int ers_users_id, String ers_username, String ers_password, String user_first_name,
			String user_last_name, String user_email, int user_role_id, String user_role) {
		
		user_row = new ERS_user(ers_users_id, ers_username, ers_password, user_first_name,
				user_last_name, user_email, user_role_id, user_role);
	}
	
}
