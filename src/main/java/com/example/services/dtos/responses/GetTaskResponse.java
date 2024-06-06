package com.example.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskResponse {
    private Long id;
    private String name;
    private List<GetLabelResponse> labels;
}
