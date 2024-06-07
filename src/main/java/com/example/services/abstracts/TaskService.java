package com.example.services.abstracts;

import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.services.dtos.requests.AddTaskRequest;
import com.example.services.dtos.requests.UpdateTaskRequest;
import com.example.services.dtos.responses.GetTaskResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface TaskService {
    DataResult<List<GetTaskResponse>> getAllTasks();
    DataResult<GetTaskResponse> getById(int id);

    Result addTask(@Valid AddTaskRequest addTaskRequest);

    Result addLabelToTask(int taskId, int labelId);

    Result updateTask(UpdateTaskRequest updateTaskRequest);

    Result deleteTask(int id);

    //get all tasks by label id
    DataResult<List<GetTaskResponse>> getAllTasksByLabelId(int labelId);
}
