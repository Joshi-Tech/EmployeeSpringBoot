package com.vivahsanskar.vivahsanskar.controllers;

import com.vivahsanskar.vivahsanskar.entity.Users;
import com.vivahsanskar.vivahsanskar.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UsersService usersService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<Users> allUsers = usersService.getAll();
        if (!allUsers.isEmpty() && allUsers!=null){
            return new ResponseEntity<>(allUsers, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    void createAdminUser(Users users){
        usersService.saveAdminUser(users);
    }
}
