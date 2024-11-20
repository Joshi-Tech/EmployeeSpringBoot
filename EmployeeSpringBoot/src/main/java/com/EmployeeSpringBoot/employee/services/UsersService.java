package com.EmployeeSpringBoot.employee.services;

import com.EmployeeSpringBoot.employee.repository.IUsersRepository;
import com.EmployeeSpringBoot.employee.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersService {
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUsersUpdatedDetails(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("User"));
        usersRepository.save(users);
    }

    public void saveUserDetails(Users users) {
        usersRepository.save(users);
    }

    public void saveAdminUser(Users users){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if(userName.equals(users.getUserName())) {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setRoles(Arrays.asList("USER", "ADMIN"));
            usersRepository.save(users);
        }
    }

    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    public Optional<Users> findById(ObjectId id) {
        return usersRepository.findById(id);
    }

    public void deleteUserById(ObjectId id) {
        usersRepository.deleteById(id);
    }

    public Users findByUserName(String userName) {
        return usersRepository.findByUserName(userName);
    }
}
