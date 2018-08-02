package com.telino.aspectj.service;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true)
public class HelloService {
	
	@Autowired
	private HelloRepository helloRepository;
	
	public String sayHello() {
		return "Hello " + helloRepository.getName();
	}

}
