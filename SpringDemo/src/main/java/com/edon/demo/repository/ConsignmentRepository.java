package com.edon.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.edon.demo.model.Consignment;

public interface ConsignmentRepository extends CrudRepository<Consignment, Integer> {
	Consignment findById(int id);
}
