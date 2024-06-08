package com.example.services.dtos.requests;

import com.example.models.Label;
import com.example.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String name;

    @NotEmpty(message = "The label ids cannot be empty.")
    private List<Integer> labelIds;

    @NotEmpty(message = "The user id cannot be empty.")
    private int userId;
}
