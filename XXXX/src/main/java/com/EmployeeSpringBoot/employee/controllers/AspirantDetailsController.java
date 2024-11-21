package com.EmployeeSpringBoot.employee.controllers;

import com.EmployeeSpringBoot.employee.entity.EmployeeDetails;
import com.EmployeeSpringBoot.employee.entity.Users;
import com.EmployeeSpringBoot.employee.repository.IEmployeeDetails;
import com.EmployeeSpringBoot.employee.repository.IUsersRepository;
import com.EmployeeSpringBoot.employee.services.EmployeeDetailsService;
import com.EmployeeSpringBoot.employee.services.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aspirants")
public class AspirantDetailsController {
    @Autowired
    private EmployeeDetailsService employeeDetailsService;
    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    IEmployeeDetails iEmployeeDetails;
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<?> getCompleteUsersDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = usersService.findByUserName(userName);
        List<EmployeeDetails> employeeDetails = user.getEmployeeDetailsList(); // Get aspirant's details

        if (employeeDetails != null && !employeeDetails.isEmpty()) {
            return new ResponseEntity<>(employeeDetails, HttpStatus.OK); // Return JSON of aspirant details
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if no details
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getAnAspirantDetails(@PathVariable ObjectId myId) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Find the user by username
        Users user = usersService.findByUserName(userName);

        // Check if the aspirant with the given ID is in the user's list
        Optional<EmployeeDetails> aspirantDetailsOptional = user.getEmployeeDetailsList()
                .stream()
                .filter(aspirant -> aspirant.getId().equals(myId))
                .findFirst();

        if (aspirantDetailsOptional.isPresent()) {
            // Return the aspirant details if found
            return new ResponseEntity<>(aspirantDetailsOptional.get(), HttpStatus.OK);
        }

        // Return 404 if the aspirant details are not found for the authenticated user
        return new ResponseEntity<>("Aspirant details not found for the user", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EmployeeDetails> userDetails(@RequestBody EmployeeDetails myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            employeeDetailsService.saveDetails(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public void removeAnAspirantDetails(@PathVariable ObjectId myId, @PathVariable String userName) {
        Users user = usersService.findByUserName(userName);
        user.getEmployeeDetailsList().removeIf(x -> x.getId().equals(myId));
        usersService.saveUsersUpdatedDetails(user);
        iEmployeeDetails.deleteById(myId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAspirantDetails(
            @PathVariable ObjectId id,
            @RequestBody EmployeeDetails updatedDetails) {

        // Get the currently authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Fetch the user from the database
        Users user = usersService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Check if the aspirant with the given ID belongs to the authenticated user
        List<EmployeeDetails> userAspirants = user.getEmployeeDetailsList();
        Optional<EmployeeDetails> aspirantOpt = userAspirants.stream()
                .filter(aspirant -> aspirant.getId().equals(id))
                .findFirst();

        if (aspirantOpt.isEmpty()) {
            return new ResponseEntity<>("Aspirant details not found for the user", HttpStatus.NOT_FOUND);
        }

        EmployeeDetails aspirant = aspirantOpt.get();

        // Update the details
        aspirant.setFirstName(updatedDetails.getFirstName());
        aspirant.setLastName(updatedDetails.getLastName());
        aspirant.setAge(updatedDetails.getAge());
        aspirant.setGender(updatedDetails.getGender());
        aspirant.setProfession(updatedDetails.getProfession());
        aspirant.setQualification(updatedDetails.getQualification());

        // Save the updated details
        employeeDetailsService.saveDetails(aspirant);

        return new ResponseEntity<>(aspirant, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAspirantDetails(@PathVariable ObjectId id) {
        // Get the currently authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Fetch the user from the database
        Users user = usersService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Check if the aspirant with the given ID belongs to the authenticated user
        List<EmployeeDetails> userAspirants = user.getEmployeeDetailsList();
        Optional<EmployeeDetails> aspirantOpt = userAspirants.stream()
                .filter(aspirant -> aspirant.getId().equals(id))
                .findFirst();

        if (aspirantOpt.isEmpty()) {
            return new ResponseEntity<>("Aspirant details not found for the user", HttpStatus.NOT_FOUND);
        }

        // Remove the aspirant details from the user's list
        userAspirants.removeIf(aspirant -> aspirant.getId().equals(id));

        // Save the updated user details
        usersService.saveUsersUpdatedDetails(user);

        // Delete the aspirant details from the repository
        employeeDetailsService.deleteAspirantDetails(id);

        return new ResponseEntity<>("Aspirant details deleted successfully", HttpStatus.OK);
    }
}
