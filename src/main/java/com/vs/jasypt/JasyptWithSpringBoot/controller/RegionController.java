package com.vs.jasypt.JasyptWithSpringBoot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RegionController {

	@GetMapping("/regions")
	public ResponseEntity<List<?>> getRegions() {
		Object[] object = {"Abc", "Singh"};
		return new ResponseEntity<List<?>>(Arrays.asList(object), HttpStatus.OK);
	}

}
