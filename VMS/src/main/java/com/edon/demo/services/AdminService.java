package com.edon.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edon.demo.model.AdminAddUserRequest;
import com.edon.demo.model.AssignVehicle;
import com.edon.demo.model.Vehicle;
import com.edon.demo.model.Role;
import com.edon.demo.model.User;
import com.edon.demo.model.UserVehicle;
import com.edon.demo.repository.VehicleRepository;
import com.edon.demo.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private UserService userService;
	
	public User adminAddUser(AdminAddUserRequest user) {
		boolean isFirstNameFilled = !user.getFirstName().equals("");
		boolean isLastNameFilled = !user.getLastName().equals("");
		boolean isEmailFilled = !user.getEmail().equals("");
		boolean isRoleSelected = !user.getRole().equals("");
				
		if (!isFirstNameFilled || !isLastNameFilled || !isEmailFilled || !isRoleSelected
				|| !userService.isValid(user.getFirstName(), user.getLastName(), user.getEmail())) {
			return null;
		}
		
		String encodedPass = generateBcryptPassword();
		User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), encodedPass, encodedPass);
		newUser.addRole(new Role(user.getRole(), newUser));
		userRepository.save(newUser);
		return newUser;
	}
	
	public String generateBcryptPassword() {
		String encodedPass = new BCryptPasswordEncoder().encode("pass");
		return encodedPass;
	}
	
	public UserVehicle assignVehicle(AssignVehicle payload) {
		// unassign from another employee
		List<User> employees = loadAllEmployees();
		for (User employee:employees) {
			Set<Vehicle> assignedVehicles = employee.getAssignedVehicles();
			for (Vehicle vehicle : assignedVehicles) {
				if (vehicle.getId() == payload.getVehicle().getId()) {
					employee.unassignVehicle(vehicle);
					vehicle.setAssignedEmployee(null);
					vehicle.setAssignedUserName(null);
					vehicleRepository.save(vehicle);
					break;
				}
			}
		}
		
		User foundUser = userRepository.findByEmail(payload.getUserEmail());
		if (foundUser != null) {
			Vehicle vehicle = payload.getVehicle();
			Vehicle foundvehicle = vehicleRepository.findById(vehicle.getId());
			
			foundvehicle.setAssignedEmployee(foundUser);
			foundvehicle.setAssignedUserName(foundUser.getFirstName() + " " + foundUser.getLastName());
			foundUser.assignVehicle(foundvehicle);
			vehicleRepository.save(foundvehicle);
			return new UserVehicle(foundUser, foundvehicle);
		}
		return null;
	}
	
	public UserVehicle unassignVehicle(AssignVehicle payload) {
		User foundUser = userRepository.findByEmail(payload.getUserEmail());
		if (foundUser != null) {
			System.out.println("Found user " + foundUser.getFirstName());
			Vehicle vehicle = payload.getVehicle();
			Vehicle foundvehicle = vehicleRepository.findById(vehicle.getId());
			
			foundvehicle.setAssignedEmployee(null);
			foundvehicle.setAssignedUserName(null);
			
			foundUser.unassignVehicle(foundvehicle);
			System.out.println("HERE....");
			vehicleRepository.save(foundvehicle);
			return new UserVehicle(foundUser, foundvehicle);
		}
		return null;
	}
	
	public List<User> loadAllEmployees() {
		List<User> users = (List<User>) userRepository.findAll();
		List<User> employees = new ArrayList<>();
		
		for (User user:users) {
			Set<Role> roles = user.getRoles();
			for (Role role: roles) {
				if (role.getRole().equals("EMPLOYEE")) {
					employees.add(user);
				}
			}
		}
		return employees;
	}
}
