package com.edon.demo.model;

import com.edon.demo.model.Vehicle;

public class AssignVehicle {
	// assigned employee email
	private String userEmail;
	// assigned vehicle
	private Vehicle vehicle;
	// owner name
	private String ownerName;
	//owner email
	private String ownerEmail;
	
	public AssignVehicle() {}
	
	public AssignVehicle(String userEmail, Vehicle vehicle, String ownerName, String ownerEmail) {
		super();
		this.userEmail = userEmail;
		this.vehicle = vehicle;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	
}
