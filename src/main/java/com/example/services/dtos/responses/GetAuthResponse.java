package com.example.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAuthResponse {
    private String username;
    private String password;
    Set<String> authorities;

    public <R> GetAuthResponse(String username, String password, R collect) {
    }
}
