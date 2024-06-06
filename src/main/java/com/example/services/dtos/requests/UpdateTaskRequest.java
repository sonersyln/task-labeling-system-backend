package com.example.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    @NotNull(message = "The task id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;

    @NotNull(message = "The task name cannot be null.")
    @NotBlank(message = "The task name can't be empty.")
    private String name;

    private List<Integer> labelIds;
}
