package com.edon;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.edon.demo.model.User;
import com.edon.demo.repository.UserRepository;
import com.edon.demo.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringDemoApplicationTests {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	
	@Test
	void contextLoads() {
		when(repository.findAll()).thenReturn(Stream.of(new User("Eh", "A", "email@hotmail.com", "pass", "pass")).collect(Collectors.toList()));
		assertEquals(1, service.getUsers().size());
	}

	/*
	 * @Test public void deleteUserTest() { User user = new User("aee", "hd",
	 * "ehaziri@hotmail.com", "pass", "pass");
	 * repository.deleteById(user.getEmail()); verify(repository,
	 * times(1)).delete(user); }
	 */
}























