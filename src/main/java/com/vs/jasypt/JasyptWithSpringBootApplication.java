package com.vs.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vs.jasypt"})
@EnableEncryptableProperties
public class JasyptWithSpringBootApplication  {

	public static void main(String[] args) {
		System.setProperty("APP_KEYSTORE_ENCRYPTOR_PASSWORD", "localPassword");
		SpringApplication.run(JasyptWithSpringBootApplication.class, args);
	}

}