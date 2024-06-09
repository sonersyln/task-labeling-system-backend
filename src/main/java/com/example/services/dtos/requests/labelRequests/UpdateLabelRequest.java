package com.example.services.dtos.requests;

import jakarta.validation.constraints.*;
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
    @Size(min = 3, max = 40, message = "The Label name must be between {min} and {max} characters.")
    private String name;

}
