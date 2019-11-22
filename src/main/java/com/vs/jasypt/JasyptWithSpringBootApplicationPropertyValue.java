package com.vs.jasypt;

import com.vs.jasypt.utils.MyProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class JasyptWithSpringBootApplicationPropertyValue implements CommandLineRunner {

	@Value("${encrypted.property}")
	private String value;

	@Autowired
	private MyProperties myProperties;

	// form Vm Arguments
	@Value("${test.value}")
	private String envValue;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(JasyptWithSpringBootApplication.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("value :"+value);
		System.out.println("Properties File  = " + myProperties);
		System.out.println(envValue);
		String[] string = context.getBeanDefinitionNames();
		Arrays.sort(string);
		System.out.println(Arrays.toString(string));
		System.out.println(context.getEnvironment());
	}
}
