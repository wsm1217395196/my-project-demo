package com.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexCtrl {

	@GetMapping("hello")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping("api/hello")
	public String apiHello() {
		return "Hello World";
	}
	
}