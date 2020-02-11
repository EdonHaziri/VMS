package com.edon.demo.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edon.demo.model.AuthRequest;
import com.edon.demo.model.AuthResponse;
import com.edon.demo.model.User;
import com.edon.demo.services.AuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class IndexController {
	
	@Autowired
	public AuthService authService;
	
	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<?> hello() {
		Map<String, String> map = new HashMap<>();
		map.put("response", "Hello!!!");
 		return ResponseEntity.ok(map);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> validateLogin(@RequestBody AuthRequest authReq) {
		AuthResponse authRes = authService.validateLogin(authReq);
		if (authRes.isValid()) {
			return ResponseEntity.ok(authRes);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(authRes);
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> register(@RequestBody User newUser) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(newUser.getEmail());
		msg.setSubject("Email from VMS");
		msg.setText("You have been successfully registered" + newUser.getEmail());
		sender.send(msg);
		AuthResponse authRes = authService.register(newUser);
		if (authRes.isValid()) {
			return ResponseEntity.ok(authRes);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(authRes);
	
	}
}
