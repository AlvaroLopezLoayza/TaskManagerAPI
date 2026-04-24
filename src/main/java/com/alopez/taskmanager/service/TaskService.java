package com.alopez.taskmanager.service;

import com.alopez.taskmanager.dto.TaskDTO;
import com.alopez.taskmanager.exception.ResourceNotFoundException;
import com.alopez.taskmanager.model.Task;
import com.alopez.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> getAll() {
        return repo.findAll();
    }

    public Task create(Task task) {
        return repo.save(task);
    }

    public Task getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));
    }

    public Task update(Long id, TaskDTO dto) {
        Task task = getById(id);
        task.setTitle(dto.getTitle());
        return repo.save(task);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
