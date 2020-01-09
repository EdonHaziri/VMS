package com.VMS.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.VMS.model.Vehicle;
import com.VMS.services.VehicleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value="/vehicles/all", method=RequestMethod.POST)
	public ResponseEntity<?> getAllvehicles(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getVehicles(email));
	}
	
	@RequestMapping(value="/vehicles/notRepaired", method=RequestMethod.POST)
	public ResponseEntity<?> getAllvehiclesNotReceived(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getAllVehiclesNotReceived(email));
	}
	
	@RequestMapping(value="/vehicles/repaired", method=RequestMethod.POST)
	public ResponseEntity<?> getAllvehiclesReceived(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getAllVehiclesReceived(email));
	}
	
	@RequestMapping(value="/vehicles/add", method=RequestMethod.POST)
	public ResponseEntity<?> addvehicle(@RequestBody Vehicle payload) {
		boolean valid = vehicleService.addVehicle(payload.getCustomer(), payload.getVehicle());
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
	
	@RequestMapping(value="/vehicles/delivered", method=RequestMethod.POST)
	public ResponseEntity<?> delivervehicle(@RequestBody Vehicle payload) {
		Vehicle res = vehicleService.deliverVehicle(payload.getCustomer(), payload.getVehicle());
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
}
