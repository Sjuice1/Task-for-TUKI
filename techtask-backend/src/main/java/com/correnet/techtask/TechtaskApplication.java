package com.correnet.techtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechtaskApplication {

	public static void main(String[] args) {
		System.setProperty("spring.amqp.deserialization.trust.all","true");
		SpringApplication.run(TechtaskApplication.class, args);
	}

}
