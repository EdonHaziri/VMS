package com.edon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edon.dao.UserRepo;
import com.edon.model.User;


@Service
public class UserService {

	UserRepo repo;
	
	public List<User> getAllUser(){
		return (List<User>) repo.findAll();
	}
}
