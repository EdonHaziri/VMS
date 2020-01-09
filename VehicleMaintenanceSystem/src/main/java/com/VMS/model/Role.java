package com.VMS.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	
	@Id
	@Column(name = "role")
	private String role;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private Set<Customer> users = new HashSet<>();
	
	public Role() {}
	
	public Role(String role, Customer newUser) {
		this.role = role;
		this.users.add(newUser);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsers(Set<Customer> users) {
		this.users = users;
	}
}
	