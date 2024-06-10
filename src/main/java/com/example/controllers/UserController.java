package com.example.controllers;

import com.example.services.concretes.UserManager;
import com.example.services.dtos.requests.userRequests.AddUserRequest;
import com.example.services.dtos.requests.userRequests.MernisRequest;
import com.example.services.dtos.requests.userRequests.SignInRequest;
import com.example.services.dtos.responses.GetAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserManager userManager;

    @PostMapping("/login")
    public GetAuthResponse login(@RequestBody SignInRequest request) throws Exception {
        return this.userManager.signIn(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody AddUserRequest request) throws Exception {
        this.userManager.createUser(request);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody MernisRequest request) throws Exception {
        this.userManager.createUserIdCardValidation(request);
    }

    //TODO
    // GetbyId oluştur ve task ekleme methodunda bunu kullan
}
