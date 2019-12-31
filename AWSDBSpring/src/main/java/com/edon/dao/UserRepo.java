package com.edon.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edon.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{

	
}
