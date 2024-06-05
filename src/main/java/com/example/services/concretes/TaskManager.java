package com.example.services.concretes;

import com.example.core.exceptions.NotFoundException;
import com.example.core.utilities.constants.MessageConstants;
import com.example.core.utilities.mappers.ModelMapperService;
import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.core.utilities.results.SuccessDataResult;
import com.example.core.utilities.results.SuccessResult;
import com.example.models.Task;
import com.example.repositories.TaskRepository;
import com.example.services.abstracts.TaskService;
import com.example.services.dtos.requests.AddTaskRequest;
import com.example.services.dtos.requests.UpdateTaskRequest;
import com.example.services.dtos.responses.GetTaskResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskManager implements TaskService {

    private final TaskRepository taskRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<List<GetTaskResponse>> getAllTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        List<GetTaskResponse> responses = tasks.stream().map(task -> this.mapperService
                .forResponse()
                .map(task, GetTaskResponse.class))
                .toList();

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetTaskResponse> getById(int id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.TASK.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        GetTaskResponse response = this.mapperService.forResponse().map(task, GetTaskResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());

    }

    @Override
    public Result addTask(AddTaskRequest addTaskRequest) {
        Task task = this.mapperService.forRequest().map(addTaskRequest, Task.class);

        this.taskRepository.save(task);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateTask(UpdateTaskRequest updateTaskRequest) {
        this.taskRepository.findById(updateTaskRequest.getId()).orElseThrow(() -> new NotFoundException(MessageConstants.TASK.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        Task task = this.mapperService.forRequest().map(updateTaskRequest, Task.class);

        this.taskRepository.save(task);
        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteTask(int id) {

        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.TASK.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        this.taskRepository.delete(task);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }
}
