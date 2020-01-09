package com.saran.webfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringWebFluxTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxTutorialApplication.class, args);
	}
}
