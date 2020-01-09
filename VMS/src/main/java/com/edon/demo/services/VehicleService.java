package com.edon.demo.services;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edon.demo.model.Vehicle;
import com.edon.demo.model.User;
import com.edon.demo.model.UserVehicle;
import com.edon.demo.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Set<Vehicle> getAllVehicles(String userEmail) {
		User user = userService.getUser(userEmail);
		return user.getVehicles();
	}
	
	public Map<String, Set<Vehicle>> getVehicles(String userEmail) {
		User user = userService.getUser(userEmail);
		Map<String, Set<Vehicle>> map = new HashMap<>();
		
		map.put("notReceived", new HashSet<Vehicle>());
		map.put("received", new HashSet<Vehicle>());
		
		for (Vehicle cons : user.getVehicles()) {
			if (cons.isReceived())
				map.get("received").add(cons);
			else
				map.get("notReceived").add(cons);
		}
		map.put("assigned", user.getAssignedVehicles());
		return map;
	}
	
	public Set<Vehicle> getAllVehiclesNotReceived(String userEmail) {
		Set<Vehicle> vehicles = getAllVehicles(userEmail);
		Set<Vehicle> notReceived = new HashSet<>();
		
		for (Vehicle cons:vehicles) {
			if (!cons.isReceived()) {
				notReceived.add(cons);
			}
		}
		return notReceived;
	}
	
	public Set<Vehicle> getAllVehiclesReceived(String userEmail) {
		Set<Vehicle> vehicles = getAllVehicles(userEmail);
		Set<Vehicle> received = new HashSet<>();
		
		for (Vehicle cons:vehicles) {
			if (cons.isReceived()) {
				received.add(cons);
			}
		}
		return received;
	}
	
	// CRUDs
	public boolean addVehicle(User user, Vehicle vehicle) {
		boolean isDescriptionFilled = !vehicle.getDescription().equals("");
		boolean isStreetFilled = !vehicle.getStreet().equals("");
		boolean isCityFilled = !vehicle.getCity().equals("");
		boolean isStateFilled = !vehicle.getState().equals("");
		boolean isZipcodeFilled = vehicle.getZipcode() != 0;
		boolean isCountryFilled = !vehicle.getCountry().equals("");
		boolean isPriceFilled = vehicle.getPrice() != 0;
		boolean isCurrencyFilled = !vehicle.getCurrency().equals("");
		
		if (isDescriptionFilled && isStreetFilled && isCityFilled && isStateFilled 
				&& isZipcodeFilled && isCountryFilled && isPriceFilled && isCurrencyFilled) {
			
			vehicle.setUser(user);
			vehicle.setReceived(false);
			vehicle.setOwnerName(user.getFirstName() + " " + user.getLastName());
			
			user.addVehicle(vehicle);
			vehicleRepository.save(vehicle);
			return true;
		}
		return false;
	}
	
	public UserVehicle deliverVehicle(User employee, Vehicle assignedVehicle) {
		User foundEmployee = userService.getUser(employee.getEmail());
		Vehicle foundVehicle = vehicleRepository.findById(assignedVehicle.getId());
		
		if (foundEmployee == null || foundVehicle == null)
			return null;
		
		foundVehicle.setReceived(true);
		vehicleRepository.save(foundVehicle);
		return new UserVehicle(foundEmployee, foundVehicle);
		
	}
}
