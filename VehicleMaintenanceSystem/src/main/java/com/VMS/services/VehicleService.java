package com.VMS.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VMS.model.Customer;
import com.VMS.model.Vehicle;
import com.VMS.services.CustomerService;
import com.VMS.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Set<Vehicle> getAllVehicles(String userEmail) {
		Customer customer = customerService.getCustomer(userEmail);
		return customer.getVehicles();
	}
	
	public Map<String, Set<Vehicle>> getVehicles(String userEmail) {
		Customer customer = customerService.getCustomer(userEmail);
		Map<String, Set<Vehicle>> map = new HashMap<>();
		
		map.put("notReceived", new HashSet<Vehicle>());
		map.put("received", new HashSet<Vehicle>());
		
		for (Vehicle vhs : customer.getVehicles()) {
			if (customer.isReceived())
				map.get("received").add(vhs);
			else
				map.get("notReceived").add(vhs);
		}
		map.put("assigned", customer.getVehicles());
		return map;
	}
	
	public Set<Vehicle> getAllVehiclesNotReceived(String userEmail) {
		Set<Vehicle> vehicles = getAllVehicles(userEmail);
		Set<Vehicle> notReceived = new HashSet<>();
		
		for (Vehicle vhs: vehicles) {
			if (!((Customer) vehicles).isReceived()) {
				notReceived.add(vhs);
			}
		}
		return notReceived;
	}
	
	public Set<Vehicle> getAllVehiclesReceived(String userEmail) {
		Set<Vehicle> vehicles = getAllVehicles(userEmail);
		Set<Vehicle> received = new HashSet<>();
		
		for (Vehicle vhs:vehicles) {
			if (vhs.isReceived()) {
				received.add(vhs);
			}
		}
		return received;
	}
	
	// CRUDs
	public boolean addVehicle(Customer customer, Vehicle vehicle) {
		boolean isDescriptionFilled = !vehicle.getDescription().equals("");
		boolean isPriceFilled = vehicle.getPrice() != 0;
		
		
		if (isDescriptionFilled && isPriceFilled ) {
			
			vehicle.setCustomer(customer);
			vehicle.setReceived(false);
			vehicle.setOwnerName(customer.getFirstName() + " " + customer.getLastName());
			
			customer.addVehicle(vehicle);
			vehicleRepository.save(vehicle);
			return true;
		}
		return false;
	}
	
	public Vehicle deliverConsignment(Customer customer, Vehicle assignedVehicle) {
		Customer foundCustomer= customerService.getCustomer(customer.getEmail());
		Vehicle foundVehicle = vehicleRepository.findById(assignedVehicle.getId());
		
		if (foundCustomer == null || foundVehicle == null)
			return null;
		
		foundVehicle.setReceived(true);
		vehicleRepository.save(foundVehicle);
		return new Vehicle(foundCustomer, foundVehicle);
		
	}

	public Vehicle deliverVehicle(Customer customer, Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}
}
