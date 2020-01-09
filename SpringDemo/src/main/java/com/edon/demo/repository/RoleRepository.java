package com.edon.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edon.demo.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
	Role findByRole(String role);
}
