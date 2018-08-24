package com.obbo.security.service;

import java.util.List;
import java.util.Optional;

import com.obbo.security.model.User;

public interface UserService {
	
	public List<User> findAll();

	public User save(User user);

	public void delete(Long id);

	public Optional<User> findById(Long id);
}
