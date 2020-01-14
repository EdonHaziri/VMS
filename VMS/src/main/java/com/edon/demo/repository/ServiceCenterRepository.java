package com.edon.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edon.demo.model.ServiceCenter;

@Repository
public interface ServiceCenterRepository extends CrudRepository<ServiceCenter, String> {
	ServiceCenter findByBrand(String brand);
}