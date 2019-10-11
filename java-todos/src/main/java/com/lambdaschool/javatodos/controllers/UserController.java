package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.User;
import com.lambdaschool.javatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // http://localhost:2019/users/mine
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/mine",
            produces = {"application/json"})
    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }
    //adds new user
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid
                                        @RequestBody
                                                User newuser) throws URISyntaxException
    {
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/users/userid/{userid}",
            produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        userService.delete(userid);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }





}
