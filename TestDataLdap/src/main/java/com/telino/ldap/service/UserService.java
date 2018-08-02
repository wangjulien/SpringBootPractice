package com.telino.ldap.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telino.ldap.dao.UserRepository;
import com.telino.ldap.model.User;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean authenticate(final String u, final String p) {
		return userRepository.findByUsernameAndPassword(u, p).isPresent();
	}
	
	
	  public List<String> search(final String username) {
	        List<User> userList = userRepository.findByUsernameLikeIgnoreCase(username);
	        if (userList == null) {
	            return Collections.emptyList();
	        }

	        return userList.stream()
	          .map(User::getUsername)
	          .collect(Collectors.toList());
	    }

	    public void create(final String username, final String password) {
	        User newUser = new User(username, passwordEncoder.encode(password));
	        newUser.setId(LdapUtils.emptyLdapName());
	        userRepository.save(newUser);

	    }

	    public void modify(final String username, final String password) {
	        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
	        user.setPassword(password);
	        userRepository.save(user);
	    }
}
