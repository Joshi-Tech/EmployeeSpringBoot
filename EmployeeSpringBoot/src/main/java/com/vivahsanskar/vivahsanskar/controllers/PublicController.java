package com.vivahsanskar.vivahsanskar.controllers;

import com.vivahsanskar.vivahsanskar.entity.Users;
import com.vivahsanskar.vivahsanskar.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody Users users) {
        usersService.saveUsersUpdatedDetails(users);
    }
}
