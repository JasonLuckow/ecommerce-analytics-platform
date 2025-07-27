package com.example.consumer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.model.User;
import com.example.consumer.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    //Created this class for quick testing to see if I could put data into the cassandra DB
    //Keeping it in case I need to test something later or if I want to add user features later

    @Autowired
    private UserService service;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return service.getUser(id);
    }
}
