package com.obbo.oauth2.server.service;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class User {
	
	private String userName;
	private String password;	
	private Set<GrantedAuthority> authority;	
	
	public User(String userName, String password, Set<GrantedAuthority> authority) {
		super();
		this.userName = userName;
		this.password = password;
		this.authority = authority;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<GrantedAuthority> getAuthority() {
		return authority;
	}
	public void setAuthority(Set<GrantedAuthority> authority) {
		this.authority = authority;
	}
	
}
