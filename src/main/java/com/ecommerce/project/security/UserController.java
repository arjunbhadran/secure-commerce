package com.ecommerce.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    //endpoint to register user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String role) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        //checking if the user already exists
        if(userDetailsManager.userExists(username)) {
            return "User already exists";
        }
        UserDetails user= User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles(role)
                .build();
        userDetailsManager.createUser(user);
        return "User created successfully";
    }
}