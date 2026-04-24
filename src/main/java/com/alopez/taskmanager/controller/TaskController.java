package com.alopez.taskmanager.controller;

import com.alopez.taskmanager.dto.TaskDTO;
import com.alopez.taskmanager.model.Task;
import com.alopez.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Task create(@Valid @RequestBody TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setCompleted(false);
        return service.create(task);
    }

    @PutMapping("/{id}")
    public Task update(@Valid @PathVariable Long id, @RequestBody TaskDTO dto) {
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
