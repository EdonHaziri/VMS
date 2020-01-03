package com.VMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.VMS.model.Employee;
import com.VMS.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Employee, String>{
	Vehicle findById(int id);

	void save(Vehicle vehicle);

}
