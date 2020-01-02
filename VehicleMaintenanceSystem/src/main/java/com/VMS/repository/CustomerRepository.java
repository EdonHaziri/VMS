package com.VMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VMS.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
	Customer findByEmail(String email);
	boolean existsByEmail(String email);
}
