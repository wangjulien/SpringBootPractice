package com.obbo.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.obbo.security.dao.UserRepository;
import com.obbo.security.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getAuthority());
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User save(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public void delete(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}
}
