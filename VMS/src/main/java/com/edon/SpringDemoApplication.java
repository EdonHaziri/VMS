package com.edon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		  SpringApplication.run(SpringDemoApplication.class, args);
		 }
		 
		 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		  return application.sources(SpringDemoApplication.class);
		 }
		 
}
