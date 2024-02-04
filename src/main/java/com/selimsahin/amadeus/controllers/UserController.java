package com.selimsahin.amadeus.controllers;

import com.selimsahin.amadeus.models.User;
import com.selimsahin.amadeus.services.JWTUtils;
import com.selimsahin.amadeus.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public String createUser(@RequestBody User user){
        return userService.createUser(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authenticate.isAuthenticated()) {
            return jwtUtils.generateToken(user);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
