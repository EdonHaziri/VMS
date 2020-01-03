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

import com.VMS.model.Customer;
import com.VMS.model.UpdateCustomerInfo;
import com.VMS.services.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customers/all", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> getUsers() {
		return customerService.getAllCustomers();
	}
	
	@RequestMapping(value = "/customers/get", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> getUser(@RequestBody Map<String, String> userInfo) {
		Customer foundCustomer = customerService.getCustomer(userInfo.get("email"));
		if (foundCustomer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundCustomer);
		}
		return ResponseEntity.ok(foundCustomer);
	}
	
	@RequestMapping(value = "/customers/add", method = RequestMethod.POST, produces = "application/json")
	public boolean addUser(@RequestBody Customer newUser) {
		return customerService.addUser(newUser);
	}
	
	@RequestMapping(value = "/customers/update", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UpdateCustomerInfo updated_customerInfo) {
		boolean valid = customerService.updateCustomer(updated_customerInfo.getOldEmail(), updated_customerInfo.getUpdatedCustomer());
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
	
	@RequestMapping(value = "/customers/delete", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> userInfo) {
		boolean valid = customerService.deleteUser(userInfo.get("email"));
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
}
