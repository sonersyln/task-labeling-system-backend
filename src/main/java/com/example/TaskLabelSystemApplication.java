package com.example;

import com.example.models.Role;
import com.example.services.concretes.UserService;
import com.example.services.dtos.requests.userRequests.AddUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class TaskLabelSystemApplication implements CommandLineRunner {

    private final UserService userService;

    public TaskLabelSystemApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskLabelSystemApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }



    @Override
    public void run(String... args) throws Exception {
        createAdmin();
    }

    private void createAdmin() throws Exception {
        AddUserRequest request = AddUserRequest.builder()
                .username("admin")
                .password("admin")
                .email("soner@gdgmail.com")
                .authorities(Set.of(Role.ROLE_ADMIN))
                .role(Role.ROLE_ADMIN)
                .build();

        AddUserRequest request2 = AddUserRequest.builder()
                .username("Soner")
                .password("soner")
                .email("syln.soner@gmail.com")
                .authorities(Set.of(Role.ROLE_USER))
                .role(Role.ROLE_USER)
                .build();

        if (userService.getByUserName(request.getUsername()).isEmpty()) {
            userService.createUser(request);
        }

        if (userService.getByUserName(request2.getUsername()).isEmpty()) {
            userService.createUser(request2);
        }
    }


}
