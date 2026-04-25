package com.be.pilot_project.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/api/public/test")
	public String testApi() {
		return "Tuyệt vời! Backend Spring Boot của bạn đã kết nối thành công!";
	}
}