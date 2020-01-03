package com.VMS.config;

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
	public ResponseEntity<?> getAllVehicles(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getVehicles(email));
	}
	
	@RequestMapping(value="/vehicles/notReceived", method=RequestMethod.POST)
	public ResponseEntity<?> getAllConsignmentsNotReceived(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getAllVehiclesNotReceived(email));
	}
	
	@RequestMapping(value="/vehicles/received", method=RequestMethod.POST)
	public ResponseEntity<?> getAllConsignmentsReceived(@RequestBody String email) {
		return ResponseEntity.ok(vehicleService.getAllVehiclesReceived(email));
	}
	
	@RequestMapping(value="/vehicles/add", method=RequestMethod.POST)
	public ResponseEntity<?> addVehicle(@RequestBody Vehicle payload) {
		boolean valid = vehicleService.addVehicle(payload.getCustomer(), payload.getVehicle());
		if (valid) {
			return ResponseEntity.ok(valid);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(valid);
	}
	
	@RequestMapping(value="/consignments/deliver", method=RequestMethod.POST)
	public ResponseEntity<?> deliverConsignment(@RequestBody Vehicle payload) {
		Vehicle res = vehicleService.deliverConsignment(payload.getCustomer(), payload.getVehicle());
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	}
}
