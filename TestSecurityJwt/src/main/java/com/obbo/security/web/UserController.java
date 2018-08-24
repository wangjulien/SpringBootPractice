package com.obbo.security.web;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obbo.security.dto.UserDto;
import com.obbo.security.model.User;
import com.obbo.security.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public User getOne(@PathVariable("id") Long id){
        return userService.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/signup")
    public User create(@RequestBody UserDto userDto){
        return userService.save(modelMapper.map(userDto, User.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new User(id);
    }

}