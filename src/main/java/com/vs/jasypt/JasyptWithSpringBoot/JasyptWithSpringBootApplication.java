package com.vs.jasypt.JasyptWithSpringBoot;

import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JasyptWithSpringBootApplication {

	private static final Logger LOG = LoggerFactory.getLogger(JasyptWithSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JasyptWithSpringBootApplication.class, args);
	}


}
