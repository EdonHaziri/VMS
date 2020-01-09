package com.edon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edon.demo.model.UserVehicle;
import com.edon.demo.services.VehicleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value="/vehicles/all", method=RequestMethod.POST)
	public ResponseEntity<?> getAllvehicles(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getVehicles(email));
	}
	
	@RequestMapping(value="/vehicles/notReceived", method=RequestMethod.POST)
	public ResponseEntity<?> getAllvehiclesNotReceived(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getAllVehiclesNotReceived(email));
	}
	
	@RequestMapping(value="/vehicles/received", method=RequestMethod.POST)
	public ResponseEntity<?> getAllvehiclesReceived(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getAllVehiclesReceived(email));
	}
	
	@RequestMapping(value="/vehicles/add", method=RequestMethod.POST)
	public ResponseEntity<?> addVehicle(@RequestBody UserVehicle payload) {
		boolean valid = vehicleService.addVehicle(payload.getUser(), payload.getVehicle());
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
	
	@RequestMapping(value="/vehicles/deliver", method=RequestMethod.POST)
	public ResponseEntity<?> deliverVehicle(@RequestBody UserVehicle payload) {
		UserVehicle res = vehicleService.deliverVehicle(payload.getUser(), payload.getVehicle());
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
}
