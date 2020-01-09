package com.VMS.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.VMS.model.Customer;
import com.VMS.model.Employee;
import com.VMS.services.CustomerService;
import com.VMS.services.EmployeeService;

public class EmployeeConfig {

	@RestController
	public class EmployeeController {

		@Autowired
		EmployeeService employeeService;
		
		@GetMapping("/")
		public List<Employee> getAllEmployees(){
			return EmployeeService.getAllEmployee();
		}
	}

}