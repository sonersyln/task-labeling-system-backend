package com.example.services.concretes;

import com.example.core.exceptions.NotFoundException;
import com.example.core.utilities.constants.MessageConstants;
import com.example.core.utilities.mappers.ModelMapperService;
import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.core.utilities.results.SuccessDataResult;
import com.example.core.utilities.results.SuccessResult;
import com.example.models.Label;
import com.example.models.Task;
import com.example.models.User;
import com.example.repositories.LabelRepository;
import com.example.repositories.TaskRepository;
import com.example.repositories.UserRepository;
import com.example.services.abstracts.EmailService;
import com.example.services.abstracts.TaskService;
import com.example.services.dtos.requests.AddTaskRequest;
import com.example.services.dtos.requests.UpdateTaskRequest;
import com.example.services.dtos.responses.GetTaskListResponse;
import com.example.services.dtos.responses.GetTaskResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskManager implements TaskService {

    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private ModelMapperService mapperService;

    @Override
    public DataResult<List<GetTaskListResponse>> getAllTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        List<GetTaskListResponse> responses = tasks.stream().map(task -> this.mapperService
                .forResponse()
                .map(task, GetTaskListResponse.class))
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
    public Result addTask(@Valid AddTaskRequest addTaskRequest) throws MessagingException, UnsupportedEncodingException {
        Task task = this.mapperService.forRequest().map(addTaskRequest, Task.class);
        task.setId(null);

        List<Label> labels = labelRepository.findAllById(addTaskRequest.getLabelIds());
        task.setLabels(labels);

        User user = this.userRepository.findById(addTaskRequest.getUserId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.USER.getMessage() + MessageConstants.NOT_FOUND.getMessage()));



        this.taskRepository.save(task);


            String labelNames = labels.stream().map(Label::getName).collect(Collectors.joining(", "));
            emailService.sendTaskAddEmail(user.getEmail(), user.getUsername(), task.getName(), labelNames);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateTask(UpdateTaskRequest updateTaskRequest) {

        Task task = this.taskRepository.findById(updateTaskRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.TASK.getMessage() + MessageConstants.NOT_FOUND.getMessage()));


        this.mapperService.forRequest().map(updateTaskRequest, task);

        List<Label> labels = labelRepository.findAllById(updateTaskRequest.getLabelIds());

        task.setLabels(labels);

        this.taskRepository.save(task);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteTask(int id) {

        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.TASK.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        this.taskRepository.delete(task);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }
    @Override

    public Result addLabelToTask(int taskId, int labelId) {
        Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException(MessageConstants.TASK.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        Label label = this.labelRepository.findById(labelId).orElseThrow(() -> new NotFoundException(MessageConstants.LABEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        task.getLabels().add(label);

        this.taskRepository.save(task);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public DataResult<List<GetTaskResponse>> getAllTasksByLabelId(int labelId) {
        Label label = this.labelRepository.findById(labelId).orElseThrow(() -> new NotFoundException(MessageConstants.LABEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        List<Task> tasks = label.getTasks();
        List<GetTaskResponse> responses = tasks.stream().map(task -> this.mapperService
                .forResponse()
                .map(task, GetTaskResponse.class))
                .toList();

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }
}
