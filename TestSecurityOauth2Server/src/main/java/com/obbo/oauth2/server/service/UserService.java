package com.obbo.oauth2.server.service;

import java.util.List;

public interface UserService {
	
	public List<User> findAll();

	public User save(User user);

	public void delete(Long id);
}
