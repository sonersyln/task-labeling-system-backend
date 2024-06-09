package com.example.services.dtos.requests.labelRequests;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLabelRequest {
    @NotNull(message = "The Label name cannot be null.")
    @NotBlank(message = "The Label name can't be empty.")
    @Size(min = 3, max = 40, message = "The Label name must be between {min} and {max} characters.")
    private String name;
}
