package com.edon.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.edon.demo.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
	Vehicle findById(int id);
}
