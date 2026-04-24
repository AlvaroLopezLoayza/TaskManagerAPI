package com.alopez.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

//    public ApiError(LocalDateTime timestamp, int status, String error, String message, String path) {
//        this.timestamp = timestamp;
//        this.status = status;
//        this.error = error;
//        this.message = message;
//        this.path = path;
//    }
}
