package com.vs.jasypt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JyscryptService {

	@Value("${encrypted.property}")
	private String property;

	public String getProperty(){
		return property;
	}

	public String getPassword(Environment environment){
		return environment.getProperty("encrypted.property");
	}
}

