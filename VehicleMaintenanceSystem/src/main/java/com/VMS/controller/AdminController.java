package com.VMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.VMS.model.Employee;
import com.VMS.services.AdminService;
import com.VMS.model.*;



@RestController
public class AdminController {
	
	@Autowired
	public AdminService adminService;
	
	@RequestMapping(value = "admin/employees/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> loadAllEmployees() {
		List<Employee> employees = adminService.loadAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@RequestMapping(value = "admin/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAddUser(@RequestBody AdminAddUserRequest Employee) {
		Employee newEmployee = adminService.adminAddUser(Employee);
		if (newEmployee != null) {
			return ResponseEntity.ok(newEmployee);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newEmployee);
	}
	
	@RequestMapping(value = "admin/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAssignEmployee(@RequestBody AssignConsignment payload) {
		Employee res = adminService.assignConsignment(payload);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	@RequestMapping(value = "admin/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminUnassignEmployee(@RequestBody AssignConsignment payload) {
		Employee res = adminService.unassignConsignment(payload);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
}
