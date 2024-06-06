package com.example.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLabelRequest {
    @NotNull(message = "The label id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;

    @NotNull(message = "The label name cannot be null.")
    @NotBlank(message = "The label name can't be empty.")
    private String name;

}
