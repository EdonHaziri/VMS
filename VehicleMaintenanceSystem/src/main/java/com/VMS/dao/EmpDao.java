package com.VMS.dao;

import java.util.List;

import com.VMS.model.Employee;

public interface EmpDao {
	List<Employee> findAllEmps();
	void saveEmp(Employee emp);
	void updateEmp(Employee emp);
	void deleteEmp(int empId);
	Employee findById(int empId);
	
}
