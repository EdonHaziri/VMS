package com.VMS.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "customer")
public class Customer {

	    private String name;
	    private String address;
	    private String contactNumber;
	    private String emailId;
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
		public void setContact(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		
		@Basic
	    @Column(name = "emailId", nullable = true, length = 255)
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
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
