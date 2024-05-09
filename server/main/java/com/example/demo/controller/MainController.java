package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/srs")
@CrossOrigin(origins = "*")
public class MainController {
	@GetMapping("/")
	public String welcome() {
		System.out.println("Welcome fn of main controller");
		return "<h1>Hello welcome to comviva</h1>";
	}

}
