package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Employee;

public class TestReimbursmentService {
	private static ReimbursmentService reimbursmentService = new ReimbursmentService();

	private static Employee user; 
	private static Employee InvalidUser; 
	private static Employee admin; 

	@BeforeEach
	public void newUser() {
		user = new Employee(0, "hong", "hong", "hong", "wu", "hwu@mail.com", 1, "Employee");
		InvalidUser = new Employee(0, "Invalid", "Invalid", "Invalid", "Invalid", "Invalid", 1, "Employee");
		admin = new Employee(0, "admin", "ae8eeac54d9e4f6871072611c630d3ef:d1c458714c745916ee396f67532c7ea5604a1299dd2851eb49ff307906e4cd9992cfa8a21b0e504f53c1ec7f87d467d9c8793c7527e5894529f1f0bd549c036a", "admin", "admin", "hwu@mail.com", 2, "Manager");
	}
	
	@Test
	public void testGetOneUserPendingRequestValid() {
		assertNotNull(reimbursmentService.getPendingRequest(user.getErs_username()));
	}


	@Test
	public void testGetOneUserPastRequestValid() {
		assertNotNull(reimbursmentService.getPastRequest(user.getErs_username()));
	}

	@Test
	public void testGetAllRequestValid() {
		assertNotNull(reimbursmentService.getAllRequest());
	}

	@Test
	public void testGetPendingRequestValid() {
		assertNotNull(reimbursmentService.getPendingRequest());
	}

}
