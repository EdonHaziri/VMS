package com.edon.demo.controller;
// add error checking for existing admin


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edon.demo.model.AdminAddServiceCenter;
import com.edon.demo.model.AdminAddUserRequest;
import com.edon.demo.model.AssignVehicle;
import com.edon.demo.model.AuthResponse;
import com.edon.demo.model.ServiceCenter;
import com.edon.demo.model.User;
import com.edon.demo.model.UserVehicle;
import com.edon.demo.services.AdminService;
import com.edon.demo.services.AuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {
	
	@Autowired
	public AdminService adminService;
	
	@RequestMapping(value = "admin/employees/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> loadAllEmployees() {
		List<User> employees = adminService.loadAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@RequestMapping(value = "admin/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAddUser(@RequestBody AdminAddUserRequest user) {
		User newUser = adminService.adminAddUser(user);
		if (newUser != null) {
			return ResponseEntity.ok(newUser);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newUser);
	}
	
	@RequestMapping(value = "admin/addServiceCenter", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAddServiceCenter(@RequestBody AdminAddServiceCenter serviceCenter) {
		ServiceCenter newServiceCenter = adminService.AdminAddServiceCenter(serviceCenter);
		if (newServiceCenter != null) {
			return ResponseEntity.ok(newServiceCenter);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(newServiceCenter);
	}
	
	
	@RequestMapping(value = "admin/assign", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminAssignEmployee(@RequestBody AssignVehicle payload) {
		UserVehicle res = adminService.assignVehicle(payload);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
	
	@RequestMapping(value = "admin/unassign", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> adminUnassignEmployee(@RequestBody AssignVehicle payload) {
		UserVehicle res = adminService.unassignVehicle(payload);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		return ResponseEntity.status(422).body(res);//200
	}
}
