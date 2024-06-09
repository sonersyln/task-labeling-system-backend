package com.example.services.concretes;

import com.example.core.exceptions.NotFoundException;
import com.example.core.utilities.constants.MessageConstants;
import com.example.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsManager implements UserDetailsService {

    private final UserManager userManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userManager.getByUserName(username);

        return user.orElseThrow(() -> new NotFoundException(MessageConstants
                .USER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
    }

}
