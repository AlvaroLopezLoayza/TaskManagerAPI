package com.alopez.taskmanager.controller;

import com.alopez.taskmanager.dto.request.TaskRequest;
import com.alopez.taskmanager.dto.response.TaskResponse;
import com.alopez.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<TaskResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TaskResponse create(@RequestBody @Valid TaskRequest request) {
        return service.create(request);
    }
}