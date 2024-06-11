package com.example.services.concretes;

import com.example.models.Role;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.dtos.requests.userRequests.AddUserRequest;
import com.example.services.dtos.requests.userRequests.MernisRequest;
import com.example.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserBusinessRules userBusinessRules;


    public Optional<User> getByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User createUserIdCardValidation(MernisRequest request) throws Exception {

        userBusinessRules.checkIfExistsByEmailAndUsername(request.getEmail(), request.getUsername());
        userBusinessRules.tcKimlikDogrula(request.getIdCardNumber(), request.getName(), request.getSurname(), request.getBirthYear());
        User newUser = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).email(request.getEmail()).idCardNumber(request.getIdCardNumber()).birthYear(request.getBirthYear()).name(request.getName()).surname(request.getSurname()).accountNonExpired(true).isEnabled(true).accountNonLocked(true).credentialsNonExpired(true).role(Role.ROLE_USER)

                .build();

        return this.userRepository.save(newUser);
    }

    public User createUser(AddUserRequest request) throws Exception {

        userBusinessRules.checkIfExistsByEmailAndUsername(request.getEmail(), request.getUsername());
        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .accountNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(request.getAuthorities())
                .role(request.getRole())

                .build();

        return this.userRepository.save(newUser);
    }







}
