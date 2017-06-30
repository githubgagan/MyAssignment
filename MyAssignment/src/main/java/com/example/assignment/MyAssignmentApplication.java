package com.example.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.assignment.service.EatingService;

@SpringBootApplication
public class MyAssignmentApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MyAssignmentApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyAssignmentApplication.class, args);
	}
	
	@Autowired
	EatingService eatingService;
	
	@Bean
	public CommandLineRunner run() {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... arg0) throws Exception {
				logger.debug("Application started");
				
			}
		};
	}
}
