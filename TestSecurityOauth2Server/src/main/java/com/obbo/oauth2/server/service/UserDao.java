package com.obbo.oauth2.server.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private PasswordEncoder passowrdEncoder;

	public User findByUsername(String username) {

		return new User("wang", passowrdEncoder.encode("test"),
				Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")).stream().collect(Collectors.toSet()));
	}

	public List<User> findAll() {
		return Arrays.asList(new User("wang", passowrdEncoder.encode("test"),
				Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")).stream().collect(Collectors.toSet())));
	}

	public User save(User user) {
		return new User("wang", passowrdEncoder.encode("test"),
				Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")).stream().collect(Collectors.toSet()));
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
}
