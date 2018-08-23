package com.obbo.oauth2.server.web;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obbo.oauth2.server.service.User;
import com.obbo.oauth2.server.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/me")
	public Principal user(Principal principal) {
		LOGGER.info(principal.getName());
		return principal;
	}
	
	@Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUser(){
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
}
