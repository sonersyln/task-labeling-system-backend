package com.example.services.concretes;

import com.example.core.exceptions.InvalidPasswordException;
import com.example.core.exceptions.NotFoundException;
import com.example.core.utilities.constants.MessageConstants;
import com.example.models.Role;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.dtos.requests.authRequests.AuthRequest;
import com.example.services.dtos.requests.userRequests.AddUserRequest;
import com.example.services.dtos.requests.userRequests.SignUpRequest;
import com.example.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class AuthManager {
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User signUp(SignUpRequest request) throws Exception {

        userBusinessRules.checkIfExistsByEmailAndUsername(request.getEmail(), request.getUsername());
        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .accountNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(Set.of(Role.ROLE_USER))
                .role(Role.ROLE_USER)

                .build();

        return this.userRepository.save(newUser);
    }

    public String signIn(AuthRequest request) {
        Optional<User> user = this.userRepository.findByUsername(request.username());
        if (user.isEmpty()) {
            throw new NotFoundException(MessageConstants.USER_NOT_FOUND.getMessage());
        }
        if (!passwordEncoder.matches(request.password(), user.get().getPassword())) {
            throw new InvalidPasswordException(MessageConstants.INVALID_PASSWORD.getMessage());
        }

        return generateToken(request);
    }

    public String generateToken(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }

        log.info("Invalid username: " + request.username());
        throw new UsernameNotFoundException("Invalid username: " + request.username());
    }
}
