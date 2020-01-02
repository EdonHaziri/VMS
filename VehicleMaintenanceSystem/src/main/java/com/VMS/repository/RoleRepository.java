package com.VMS.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.VMS.model.Role;

@Repository
public interface RoleRepository extends CrudRepository{
	Role findByRole(String role);
}
