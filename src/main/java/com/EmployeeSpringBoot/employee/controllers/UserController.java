package com.EmployeeSpringBoot.employee.controllers;

import com.EmployeeSpringBoot.employee.entity.Users;
import com.EmployeeSpringBoot.employee.repository.IUsersRepository;
import com.EmployeeSpringBoot.employee.services.EmployeeDetailsService;
import com.EmployeeSpringBoot.employee.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private IUsersRepository iUsersRepository;

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAll();
    }


    @GetMapping("{userName}")
    public ResponseEntity<?> getUser(@PathVariable String userName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (name != null && name.equals(userName)) {
            Users byUserName = iUsersRepository.findByUserName(name);
            return new ResponseEntity<>(byUserName, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("{userByName}")
    public ResponseEntity<?> updateAUser(@RequestBody Users users, @PathVariable String userByName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users usersDB = usersService.findByUserName(userName);
        usersDB.setUserName(usersDB.getUserName());
        usersDB.setPassword(users.getPassword());
        usersService.saveUsersUpdatedDetails(usersDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users users) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users usersDB = usersService.findByUserName(userName);
        usersDB.setUserName(usersDB.getUserName());
        usersDB.setPassword(users.getPassword());
        usersService.saveUsersUpdatedDetails(usersDB);
        return new ResponseEntity<>(usersDB, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        iUsersRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
