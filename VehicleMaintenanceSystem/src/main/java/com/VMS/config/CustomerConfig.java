package com.VMS.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.VMS.model.Customer;
import com.VMS.services.CustomerService;

public class CustomerConfig {

	@RestController
	public class EmployeeController {

		@Autowired
		CustomerService customerService;
		
		@GetMapping("/")
		public List<Customer> getAllCustomers(){
			return customerService.getAllCustomers();
		}
	}

}
