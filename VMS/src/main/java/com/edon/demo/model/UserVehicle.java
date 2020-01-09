package com.edon.demo.model;

import com.edon.demo.model.Vehicle;
import com.edon.demo.model.User;

public class UserVehicle {
	private User user;
	private Vehicle vehicle;
	
	public UserVehicle() {}

	public UserVehicle(User user, Vehicle vehicle) {
		super();
		this.user = user;
		this.vehicle = vehicle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
