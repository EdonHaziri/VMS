package com.VMS.model;

import com.VMS.interfaces.Deletable;
import com.VMS.interfaces.*;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vehicle implements HasLocationId, Deletable {
    private Integer id;
    private String manufacturer;
    private String model;
    private String registration;
    private String description;
    private Byte deleted;
    private Integer year;
    private Integer price;
    private Integer locationId;
	private Integer engineNumber;

    

	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "deleted", nullable = true)
    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }


    @Basic
    @Column(name = "year", nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "registration", nullable = false, length = 64)
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    
    @Basic
    @Column(name = "engineNumber", nullable = false)
    public Integer getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(Integer engineNumber) {
		this.engineNumber = engineNumber;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                ", registration='" + registration + '\'' +
                ", price=" + price +
                ", locationId=" + locationId +
                ", engineNumber='" + engineNumber + '\'' +
                '}';
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "location_id", nullable = true)
    public Integer getLocationId() {
        return locationId;
    }


    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
    
    @Basic
    @Column(name = "manufacturer", nullable = false)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    @Basic
    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Vehicle(){

    }
    public Vehicle(Integer id, String manufacturer, String model, String registration, String description, Byte deleted, Integer year, Integer price, Integer locationId, Integer engineNumber) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.registration = registration;
        this.description = description;
        this.deleted = deleted;
        this.year = year;
        this.price = price;
        this.locationId = locationId;
        this.engineNumber = engineNumber;
    }

}
