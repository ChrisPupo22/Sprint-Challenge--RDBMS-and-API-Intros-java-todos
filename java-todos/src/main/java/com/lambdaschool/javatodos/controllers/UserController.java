package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // http://localhost:2019/users/mine
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
                HttpStatus.OK);
    }
}
