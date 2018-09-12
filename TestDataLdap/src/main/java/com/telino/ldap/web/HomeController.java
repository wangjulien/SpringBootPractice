package com.telino.ldap.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telino.ldap.dao.UserRepository;
import com.telino.ldap.model.User;

@RestController
public class HomeController {
	
//	@Autowired
//	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String index() {
		
		Iterable<User> names = userRepository.findAll();
		
		return "Welcome to the home page : " + StreamSupport.stream(names.spliterator(), false).map(User::getUsername).collect(Collectors.joining(","));
	}
}
