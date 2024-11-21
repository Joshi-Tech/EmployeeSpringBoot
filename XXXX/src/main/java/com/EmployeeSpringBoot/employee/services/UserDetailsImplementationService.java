/*
package com.vivahsanskar.vivahsanskar.services;

import com.vivahsanskar.vivahsanskar.entity.Users;
import com.vivahsanskar.vivahsanskar.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public
class UserDetailsImplementationService implements UserDetailsService {

    @Autowired
    private IUsersRepository iUsersRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = iUsersRepository.findByUserName(userName);
        if(users!=null){
            UserDetails userDetails = User.builder()
                    .username(users.getUserName())
                    .password(users.getPassword())
                    .roles(users.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }

        throw new UsernameNotFoundException(userName+" user not found!!");
    }
}
*/


package com.EmployeeSpringBoot.employee.services;

import com.EmployeeSpringBoot.employee.repository.IUsersRepository;
import com.EmployeeSpringBoot.employee.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImplementationService implements UserDetailsService {

    @Autowired
    private IUsersRepository iUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        try {
            Users users = iUsersRepository.findByUserName(userName);

            if (users != null) {
                return User.builder()
                        .username(users.getUserName())
                        .password(users.getPassword())
                        .roles(users.getRoles().toArray(new String[0]))
                        .build();
            } else {
                // Throw exception if user not found
                throw new UsernameNotFoundException(userName + " user not found!!");
            }
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException("An error occurred while loading the user", e);
        }
    }
}
