package com.obbo.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.obbo.security.dao.RoleRepository;
import com.obbo.security.dao.UserRepository;
import com.obbo.security.model.Role;
import com.obbo.security.model.Role.RoleName;
import com.obbo.security.model.User;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup) return;
						
		Role adminRole = createRoleIfNotFound(RoleName.ROLE_ADMIN);
		Role userRole = createRoleIfNotFound(RoleName.ROLE_USER);

		User adminUser = new User();
		adminUser.setUsername("admin");
		adminUser.setPassword(passwordEncoder.encode("test"));
		adminUser.addRole(adminRole);
		createUserIfNotFound(adminUser);
		
		User normUser = new User();
		normUser.setUsername("guest");
		normUser.setPassword(passwordEncoder.encode("test"));
		normUser.addRole(userRole);
		createUserIfNotFound(normUser);

		alreadySetup = true;
	}

	
	@Transactional
	private Role createRoleIfNotFound(RoleName name) {

		Optional<Role> optRole = roleRepository.findByName(name.toString());

		return optRole.orElseGet(() -> roleRepository.save(new Role(name.toString())));
	}
	
	@Transactional
	private User createUserIfNotFound(User newUser) {

		Optional<User> optUser = userRepository.findByUsername(newUser.getUsername());

		return optUser.orElseGet(() -> userRepository.save(newUser));
	}
}