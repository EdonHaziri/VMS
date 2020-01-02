package com.VMS.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

		private String firstName;
	    private String lastName;
	    private String contactNumber;
	    private String emailId;
	    private String password;
	    private String EmployeeType;
	    
	    
		@Basic
	    @Column(name = "firstName", nullable = true, length = 255)
		public String getFirstName() {
			return firstName;
		}
		public void setfirstName(String firstName) {
			this.firstName = firstName;
		}
		
		@Basic
	    @Column(name = "lastName", nullable = true, length = 255)
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
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
		
		@Basic
	    @Column(name = "employeeType", nullable = true, length = 255)
		public String getEmployeeType() {
			return EmployeeType;
		}
		public void setEmployeeType(String employeeType) {
			EmployeeType = employeeType;
		}
	    
}
