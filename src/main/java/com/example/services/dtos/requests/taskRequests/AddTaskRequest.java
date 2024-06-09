package com.example.services.dtos.requests.taskRequests;

import com.example.models.Label;
import com.example.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {
    @NotNull(message = "The task name cannot be null.")
    @NotBlank(message = "The task name can't be empty.")
    @Size(min = 3, max = 80, message = "Task must be between {min} and {max} characters.")
    private String name;

    @NotEmpty(message = "The label ids cannot be empty.")
    private List<Integer> labelIds;

    @NotNull(message = "The username cannot be null.")
    @NotBlank(message = "The username can't be empty.")
    private String username;
}
