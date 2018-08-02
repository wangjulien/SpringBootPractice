package com.telino.ldap.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telino.ldap.dao.PersonRepository;

@RestController
public class HomeController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/")
	public String index() {
		
		List<String> names = personRepository.getAllPersonNames();
		
		return "Welcome to the home page : " + names;
	}
}
