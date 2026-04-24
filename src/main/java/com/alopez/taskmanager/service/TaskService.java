package com.alopez.taskmanager.service;

import com.alopez.taskmanager.dto.TaskDTO;
import com.alopez.taskmanager.exception.ResourceNotFoundException;
import com.alopez.taskmanager.model.Task;
import com.alopez.taskmanager.model.User;
import com.alopez.taskmanager.repository.TaskRepository;
import com.alopez.taskmanager.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository repo;
    private final UserRepository userRepo;

    private String getCurrentUser() {
        return (String) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();
    }

    public TaskService(TaskRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public List<Task> getAll() {
        String username = getCurrentUser();
        return repo.findByUserUsername(username);
    }

    public Task create(Task task) {
        String username = getCurrentUser();

        User user = userRepo.findByUsername(username).orElseThrow();

        task.setUser(user);

        return repo.save(task);
    }

    public Task getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found"));
    }

    public Task update(Long id, TaskDTO dto) {
        Task task = getById(id);

        if(!task.getUser().getUsername().equals(getCurrentUser())) {
            throw new ResourceNotFoundException("Forbidden");
        }

        if(dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        task.setCompleted(dto.getCompleted());
        return repo.save(task);
    }

    public void delete(Long id) {
        Task task = getById(id);

        if(!task.getUser().getUsername().equals(getCurrentUser())) {
            throw new RuntimeException("Forbidden");
        }

        repo.deleteById(id);
    }
}
