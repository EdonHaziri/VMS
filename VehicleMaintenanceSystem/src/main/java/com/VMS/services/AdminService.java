package com.VMS.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.VMS.model.AdminAddUserRequest;
import com.VMS.model.AssignVehicle;
import com.VMS.model.Vehicle;
import com.VMS.model.Role;
import com.VMS.model.Customer;
import com.VMS.model.Vehicle;
import com.VMS.repository.VehicleRepository;
import com.VMS.repository.CustomerRepository;

@Service
public class AdminService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VehicleRepository VehicleRepository;
	
	@Autowired
	private CustomerService customerService;
	
	public Customer adminAddUser(AdminAddUserRequest user) {
		boolean isFirstNameFilled = !user.getFirstName().equals("");
		boolean isLastNameFilled = !user.getLastName().equals("");
		boolean isEmailFilled = !user.getEmail().equals("");
		boolean isRoleSelected = !user.getRole().equals("");
				
		if (!isFirstNameFilled || !isLastNameFilled || !isEmailFilled || !isRoleSelected
				|| !customerService.isValid(user.getFirstName(), user.getLastName(), user.getEmail())) {
			return null;
		}
		
		String encodedPass = generateBcryptPassword();
		Customer newUser = new Customer(user.getFirstName(), user.getLastName(), user.getEmail(), encodedPass, encodedPass);
		newUser.addRole(new Role(user.getRole(), newUser));
		customerRepository.save(newUser);
		return newUser;
	}
	
	public String generateBcryptPassword() {
		String encodedPass = new BCryptPasswordEncoder().encode("pass");
		return encodedPass;
	}
	
	public Vehicle assignVehicle(AssignVehicle payload) {
		// unassign from another customer
		List<Customer> customer = loadAllCustomers();
		List<Vehicle> vehicle = null;
		for (Vehicle vehicles : vehicle) {
			Set<Vehicle> assignedVehicles = vehicles.getAssignedVehicle();
			for (Vehicle Vehicle : assignedVehicles) {
				if (Vehicle.getId() == payload.getVehicle().getId()) {
					vehicles.unassignVehicle(Vehicle);
					Vehicle.setAssignedCustomer(null);
					Vehicle.setAssignedUserName(null);
					VehicleRepository.save(Vehicle);
					break;
				}
			}
		}
		
		Customer foundCustomer = customerRepository.findByEmail(payload.getOwnerEmail());
		if (foundCustomer != null) {
			Vehicle Vehicle = payload.getVehicle();
			Vehicle foundVehicle = VehicleRepository.findById(Vehicle.getId());
			
			foundVehicle.setAssignedCustomer(foundCustomer);
			foundVehicle.setAssignedUserName(foundCustomer.getFirstName() + " " + foundCustomer.getLastName());
			foundCustomer.assignVehicle(foundVehicle);
			VehicleRepository.save(foundVehicle);
			return new Vehicle(foundCustomer, foundVehicle);
		}
		return null;
	}
	
	public List<Customer> loadAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vehicle unassignVehicle(AssignVehicle payload) {
		Customer foundCustomer = customerRepository.findByEmail(payload.getOwnerEmail());
		if (foundCustomer != null) {
			System.out.println("Found user " + foundCustomer.getFirstName());
			Vehicle Vehicle = payload.getVehicle();
			Vehicle foundVehicle = VehicleRepository.findById(Vehicle.getId());
			
			foundVehicle.setAssignedCustomer(null);
			foundVehicle.setAssignedUserName(null);
			
			foundCustomer.unassignVehicle(foundVehicle);
			System.out.println("HERE....");
			VehicleRepository.save(foundVehicle);
			return new Vehicle(foundCustomer, foundVehicle);
		}
		return null;
	}
	
	public List<Customer> loadAllEmployees() {
		List<Customer> users = (List<Customer>) customerRepository.findAll();
		List<Customer> employees = new ArrayList<>();
		
		for (Customer Customer:users) {
			Set<Role> roles = Customer.getRoles();
			for (Role role: roles) {
				if (role.getRole().equals("EMPLOYEE")) {
					employees.add(Customer);
				}
			}
		}
		return employees;
	}
}
