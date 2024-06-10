package com.example.services.dtos.requests.userRequests;

import com.example.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MernisRequest {
    private String username;
    private String password;
    private String email;
    private Long idCardNumber;
    private String name;
    private String surname;
    private int birthYear;
}
