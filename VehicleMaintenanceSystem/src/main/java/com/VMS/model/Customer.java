package com.VMS.model;

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

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name = "user")
public class Customer {
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "confirmPassword")
	private String confirmPassword;
	
	@ManyToMany(targetEntity=Role.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="Customer_Role", 
		joinColumns= {@JoinColumn(name="email")}, 
		inverseJoinColumns={@JoinColumn(name="role")}
	)
	private Set<Role> roles = new HashSet<>();
	
	// owned vehicles
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Vehicle.class)
	private Set<Vehicle> consignments = new HashSet<>();
	
	// assigned vehicles
	@OneToMany(mappedBy = "assignedEmployee", fetch = FetchType.EAGER, targetEntity=Vehicle.class)
	private Set<Vehicle> assignedConsignments = new HashSet<>();
		
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
	}
	
	public void addConsignment(Vehicle vehicle) {
		consignments.add(vehicle);
	}
	
	public void removeConsignment(Vehicle vehicle) {
		consignments.remove(vehicle);
	}
	
	public void assignConsignment(Vehicle vehicle) {
		assignedConsignments.add(vehicle);
	}
	
	public void unassignConsignment(Vehicle vehicle) {
		assignedConsignments.remove(vehicle);
	}
	
	public Customer() {
		
	}
	
	public Customer(String firstName, String lastName, String email, String password, String confirmPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	
}
