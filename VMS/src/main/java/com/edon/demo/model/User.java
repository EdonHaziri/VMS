package com.edon.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name = "user")
public class User {
	
	@Column(name = "firstName")
	@Getter @Setter
	private String firstName;
	
	@Column(name = "lastName")
	@Getter @Setter
	private String lastName;
	
	@Id
	@Column(name = "email")
	@Getter @Setter
	private String email;
	
	@Column(name = "password")
	@Getter @Setter
	private String password;
	
	@Column(name = "confirmPassword")
	@Getter @Setter
	private String confirmPassword;
	
	@ManyToMany(targetEntity=Role.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="User_Role", 
		joinColumns= {@JoinColumn(name="email")}, 
		inverseJoinColumns={@JoinColumn(name="role")}
	)
	@Getter @Setter
	private Set<Role> roles = new HashSet<>();
	
	// owned vehicles
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Vehicle.class)
	@Getter @Setter
	private Set<Vehicle> vehicles = new HashSet<>();
	
	// assigned vehicles
	@OneToMany(mappedBy = "assignedEmployee", fetch = FetchType.EAGER, targetEntity=Vehicle.class)
	@Getter @Setter
	private Set<Vehicle> assignedVehicles = new HashSet<>();
		
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
	}
	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
	}
	
	public void assignVehicle(Vehicle vehicle) {
		assignedVehicles.add(vehicle);
	}
	
	public void unassignVehicle(Vehicle vehicle) {
		assignedVehicles.remove(vehicle);
	}
	
	public User () {
		
	}
	
	public User(String firstName, String lastName, String email, String password, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	
}
