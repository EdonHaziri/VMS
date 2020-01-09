package com.VMS.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.VMS.model.Employee;
import com.VMS.model.UpdateEmployeeInfo;
import com.VMS.services.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/employees/all", method = RequestMethod.GET, produces = "application/json")
	public List<Employee> getEmployees() {
		return EmployeeService.getAllEmployees();
	}
	
	@RequestMapping(value = "/employees/get", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getEmployee(@RequestBody Map<String, String> employeeInfo) {
		Employee foundemployees = employeeService.getEmployee(employeeInfo.get("email"));
		if (foundemployees == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundemployees);
		}
		return ResponseEntity.ok(foundemployees);
	}
	
	@RequestMapping(value = "/employees/add", method = RequestMethod.POST, produces = "application/json")
	public boolean addEmployee(@RequestBody Employee newEmployee) {
		return employeeService.addEmployee(newEmployee);
	}
	
	@RequestMapping(value = "/employees/update", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateEmployee(@RequestBody UpdateEmployeeInfo updated_employeesInfo) {
		boolean valid = employeeService.updateEmployees(updated_employeesInfo.getOldEmail(), updated_employeesInfo.getUpdatedEmployees());
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
	
	@RequestMapping(value = "/employees/delete", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> deleteEmployee(@RequestBody Map<String, String> employeeInfo) {
		boolean valid = employeeService.deleteEmployee(employeeInfo.get("email"));
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
}
