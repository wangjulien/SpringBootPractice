package com.telino.ldap.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.ldap.repository.LdapRepository;

import com.telino.ldap.model.User;

public interface UserRepository extends LdapRepository<User> {
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
	public List<User> findByUsernameLikeIgnoreCase(String username);
}
