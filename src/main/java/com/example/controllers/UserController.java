package com.example.controllers;

import com.example.services.concretes.UserService;
import com.example.services.dtos.requests.AddUserRequest;
import com.example.services.dtos.requests.SignInRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody SignInRequest request) throws Exception {
        this.userService.singIn(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody AddUserRequest request) {
        this.userService.createUser(request);
    }
}
