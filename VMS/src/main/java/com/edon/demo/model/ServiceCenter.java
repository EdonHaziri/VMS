package com.edon.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Edon
 *s
 */
@Entity
@Table(name = "serviceCenter")
public class ServiceCenter {
	
	@Id
	@Column(name = "brand", columnDefinition = "varchar(255) default 'BMW'")
	@Getter @Setter
	private String brand;
	
	public ServiceCenter() { }

	public ServiceCenter(String brand) {
		super();
		this.brand = brand;
	}
	
}
