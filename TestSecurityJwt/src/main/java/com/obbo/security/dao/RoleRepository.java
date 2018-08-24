package com.obbo.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obbo.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

}
