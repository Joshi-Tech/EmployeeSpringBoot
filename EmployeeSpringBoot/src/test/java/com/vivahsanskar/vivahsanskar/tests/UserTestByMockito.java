package com.vivahsanskar.vivahsanskar.tests;

import com.vivahsanskar.vivahsanskar.entity.Users;
import com.vivahsanskar.vivahsanskar.repository.IUsersRepository;
import com.vivahsanskar.vivahsanskar.services.UserDetailsImplementationService;
import com.vivahsanskar.vivahsanskar.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserTestByMockito {

    /** @InjectMocks inject mock dependencies into the class being tested.
     * It is typically used in unit tests where you want to test a class
     * in isolation but still need to provide its dependencies.
     * When you annotate a class with @InjectMocks, Mockito creates an instance of that class
     * and injects its dependencies using any available:
     * Constructor injection (preferred if constructors are present).
     * Setter injection (if setters are available).
     * Field injection (if neither constructors nor setters are present).
     * In simple terms, @InjectMocks sets up the object you want to test with mock versions
     * of its dependencies
     * so you can focus on testing the behavior of the class itself.
     */
    @InjectMocks
    private UserDetailsImplementationService userDetailsImplementationService;

    @Mock
    private IUsersRepository iUsersRepository;

    @BeforeEach
    public void setUp(){
        //This will initialize the Mockito annotation
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUserByMocks(){
       Users users=  new Users("Ram","123");
    when(iUsersRepository.findByUserName("Ram")).thenReturn(users);
    assertEquals("123", users.getPassword());
        }
}
