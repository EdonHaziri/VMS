package com.VMS.model;

import com.VMS.interfaces.*;
import com.VMS.model.Vehicle;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "vehicle")
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

	public Vehicle(Customer foundCustomer, Vehicle foundVehicle) {
		// TODO Auto-generated constructor stub
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isReceived() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setReceived(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void setCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	public void setOwnerName(String string) {
		// TODO Auto-generated method stub
		
	}

	public Vehicle getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

	public void add(Vehicle vehicle) {
		// TODO Auto-generated method stub
		
	}

	public void setAssignedCustomer(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void setAssignedUserName(Object object) {
		// TODO Auto-generated method stub
		
	}
	public Set<Vehicle> getAssignedVehicle() {
		return getAssignedVehicle();
	}

	public void setAssignedVehicle(Set<AssignVehicle> assignedVehicle) {
		this.assignedVehicles = assignedVehicle;
	}
		
		public void unassignVehicle(Vehicle vehicle) {
			// TODO Auto-generated method stub
			
		}
		
		// owned vehicles
		@Column(name = "vehicle")
		@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Vehicle.class)
		@Getter @Setter
		private Set<Vehicle> vehicles = new HashSet<>();
		
		// assigned vehicles
		@Column(name = "assignedVehicle")
		@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Vehicle.class)
		@Getter @Setter
		private Set<AssignVehicle> assignedVehicles = new HashSet<>();
		

}
