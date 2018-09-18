package com.obbo.security.sso.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//	@RequestMapping({ "/user", "/me" })
//	public Map<String, String> user(Principal principal) {
//		Map<String, String> map = new LinkedHashMap<>();
//		map.put("name", principal.getName());
//		return map;
//	}
	
	@RequestMapping({"/user", "/me"})
	public Principal user(Principal principal) {
		return principal;
	}
}
