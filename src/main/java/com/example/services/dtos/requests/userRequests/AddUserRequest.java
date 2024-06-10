package com.example.services.dtos.requests.userRequests;
import com.example.models.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserRequest {
    private String username;
    private String password;
    private String email;
    Set<Role> authorities;
    private Role role;

}
