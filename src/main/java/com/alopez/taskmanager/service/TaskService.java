package com.alopez.taskmanager.service;

import com.alopez.taskmanager.dto.request.TaskRequest;
import com.alopez.taskmanager.dto.response.TaskResponse;
import com.alopez.taskmanager.mapper.TaskMapper;
import com.alopez.taskmanager.model.Task;
import com.alopez.taskmanager.model.User;
import com.alopez.taskmanager.repository.TaskRepository;
import com.alopez.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    public List<TaskResponse> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TaskResponse create(TaskRequest request) {
        Task task = mapper.toEntity(request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);

        return mapper.toResponse(taskRepository.save(task));
    }
}