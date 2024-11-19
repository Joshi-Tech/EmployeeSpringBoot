package com.vivahsanskar.vivahsanskar.services;

import com.vivahsanskar.vivahsanskar.entity.EmployeeDetails;
import com.vivahsanskar.vivahsanskar.entity.Users;
import com.vivahsanskar.vivahsanskar.repository.IEmployeeDetails;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDetailsService {
    @Autowired
    private IEmployeeDetails aspirant;
    @Autowired
    private IEmployeeDetails aspirantDetailsRepository;
    @Autowired
    private UsersService usersService;

    @Transactional
    public void saveDetails(EmployeeDetails details, String userName) {
        try {
            Users user = usersService.findByUserName(userName);
            details.setDate(LocalDate.now());
            aspirantDetailsRepository.save(details);
            EmployeeDetails saved = aspirantDetailsRepository.save(details);
            user.getEmployeeDetailsList().add(saved);
            usersService.saveUserDetails(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry: " + e);
        }
    }

    public void saveDetails(EmployeeDetails details) {
        aspirantDetailsRepository.save(details);
    }

    public List<EmployeeDetails> getAll() {
        return aspirant.findAll();
    }

    public Optional<EmployeeDetails> findById(ObjectId id) {
        return aspirant.findById(id);
    }

    public void deleteAspirantDetails(ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users users = usersService.findByUserName(userName);
        users.getEmployeeDetailsList().removeIf(x -> x.getId().equals(id));
        usersService.saveUsersUpdatedDetails(users);
        aspirant.deleteById(id);
    }
}
