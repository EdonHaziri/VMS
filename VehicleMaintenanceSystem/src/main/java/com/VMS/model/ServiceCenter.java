package com.VMS.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

public class ServiceCenter {

	private Integer id;
    private String address;
    private String contactNumber;
    private String emailId;
    
    @Id
    @Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "address", nullable = true, length = 255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Basic
    @Column(name = "contactNumber", nullable = true, length = 255)
	public String getContact() {
		return contactNumber;
	}
	public void setContact(String contact) {
		this.contactNumber = contact;
	}
	
	@Basic
    @Column(name = "emailId", nullable = true, length = 255)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
   
    
    
}
