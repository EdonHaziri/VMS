package com.VMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.VMS.model.*;
import com.VMS.services.AdminService;


@RestController
public class AdminController {
	
	@Autowired
	public AdminService adminService;
	
	@RequestMapping(value = "admin/employees/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> loadAllEmployees() {
		List<Customer> employees = adminService.loadAllCustomers();
		return ResponseEntity.ok(employees);
	}
	
	@RequestMapping(value = "admin/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAddUser(@RequestBody AdminAddUserRequest Employee) {
		Customer newEmployee = adminService.adminAddUser(Employee);
		if (newEmployee != null) {
			return ResponseEntity.ok(newEmployee);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newEmployee);
	}
	
	@RequestMapping(value = "admin/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAssignEmployee(@RequestBody AssignVehicle payload) {
		Vehicle res = adminService.assignVehicle(payload);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	@RequestMapping(value = "admin/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminUnassignEmployee(@RequestBody AssignVehicle payload) {
		Vehicle res = adminService.unassignVehicle(payload);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
}
