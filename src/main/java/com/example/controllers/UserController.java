package com.example.controllers;

import com.example.services.concretes.UserService;
import com.example.services.dtos.requests.authRequests.AuthRequest;
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

    private final UserService userService;



    @PostMapping("/createUser")
    public void createUser(@RequestBody MernisRequest request) throws Exception {
        this.userService.createUserIdCardValidation(request);
    }


    //TODO
    // GetbyId oluştur ve task ekleme methodunda bunu kullan
}
