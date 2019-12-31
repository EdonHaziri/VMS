package com.edon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.edon.model.User;
import com.edon.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	public List<User> getAllUsers(){
		return userService.getAllUser();
	}
}
