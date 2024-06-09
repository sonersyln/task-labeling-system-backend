package com.example.services.concretes;

import com.example.core.exceptions.InvalidPasswordException;
import com.example.core.exceptions.NotFoundException;
import com.example.core.utilities.constants.MessageConstants;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.dtos.requests.AddUserRequest;
import com.example.services.dtos.requests.SignInRequest;
import com.example.services.dtos.responses.GetAuthResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getByUserName(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User createUser(AddUserRequest request){
        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .accountNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(request.getAuthorities())

            .build();

        return this.userRepository.save(newUser);
    }

    public GetAuthResponse signIn(SignInRequest request){
        Optional<User> user = this.userRepository.findByUsername(request.getUsername());
        if(user.isEmpty()){
            throw new NotFoundException(MessageConstants.USER_NOT_FOUND.getMessage());
        }
        if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
            throw new InvalidPasswordException(MessageConstants.INVALID_PASSWORD.getMessage());
        }

        User foundUser = user.get();
        GetAuthResponse response = new GetAuthResponse(
                foundUser.getUsername(),
                foundUser.getPassword(),
                foundUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())
        );

        return response;
    }




}
