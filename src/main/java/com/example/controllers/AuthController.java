package com.example.controllers;

import com.example.services.concretes.AuthManager;
import com.example.services.dtos.requests.authRequests.AuthRequest;
import com.example.services.dtos.requests.userRequests.AddUserRequest;
import com.example.services.dtos.requests.userRequests.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthManager authManager;
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) throws Exception {
        return this.authManager.signIn(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody SignUpRequest request) throws Exception {
        this.authManager.signUp(request);
    }
}
