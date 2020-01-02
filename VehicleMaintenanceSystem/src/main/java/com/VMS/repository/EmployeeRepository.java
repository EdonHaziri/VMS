package com.VMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.VMS.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{
	Employee findById(int id);
}
