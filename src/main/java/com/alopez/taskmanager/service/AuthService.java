package com.alopez.taskmanager.service;

import com.alopez.taskmanager.model.User;
import com.alopez.taskmanager.repository.UserRepository;
import com.alopez.taskmanager.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public String login(String username, String password) {
        User user = repo.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return JwtUtil.generateToken(username);
    }

    public void register(String username, String password) {
        if(repo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));

        repo.save(user);
    }
}
