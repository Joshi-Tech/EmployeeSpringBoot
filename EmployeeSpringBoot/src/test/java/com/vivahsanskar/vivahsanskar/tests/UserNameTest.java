package com.vivahsanskar.vivahsanskar.tests;


import com.vivahsanskar.vivahsanskar.entity.EmployeeDetails;
import com.vivahsanskar.vivahsanskar.repository.IUsersRepository;
import com.vivahsanskar.vivahsanskar.services.EmployeeDetailsService;
import com.vivahsanskar.vivahsanskar.services.UsersService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserNameTest {

    @Autowired
    private IUsersRepository iUsersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @Test
    public void userName() {
        List<EmployeeDetails> ram = iUsersRepository.findByUserName("Ram").getEmployeeDetailsList()
                .stream().filter(x -> x.getFirstName().equals("Ram")).collect(Collectors.toList());
        List<ObjectId> collect = ram.stream().map(x -> x.getId()).collect(Collectors.toList());
        assertEquals("67360e98926924646478a3d8", collect.get(0).toString(), "id not found");
        assertEquals("Electrician", ram.stream().map(x -> x.getProfession())
                .collect(Collectors.toList())
                .get(0));


        // System.out.println("*****"+iUsersRepository.findByUserName("Ram"));
        // assertEquals("Ram",iUsersRepository.findByUserName(usersService.findByUserName()));
    }
}
