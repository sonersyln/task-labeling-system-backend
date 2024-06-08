package com.example.services.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskListResponse {
    private int id;
    private String name;
    private List<GetLabelResponse> labels;
    private GetUserResponse user;
}
