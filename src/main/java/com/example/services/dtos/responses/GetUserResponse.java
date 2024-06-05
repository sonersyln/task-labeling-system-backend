package com.example.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {

    private int id;
    private String username;
    private String password;
    private String email;
    Set<String> authorities;
}
