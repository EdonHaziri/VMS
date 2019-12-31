package com.VMS.model;

import javax.persistence.Basic;
import javax.persistence.Column;

public class Admin {

	private String name;
	private String password;
	
	@Basic
    @Column(name = "name", nullable = true, length = 255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
    @Column(name = "password", nullable = true, length = 255)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
