package com.obbo.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class TestSecurityOauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSecurityOauth2ServerApplication.class, args);
	}
}
