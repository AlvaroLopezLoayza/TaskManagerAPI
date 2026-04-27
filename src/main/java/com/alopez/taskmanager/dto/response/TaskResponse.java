package com.alopez.taskmanager.dto.response;

import lombok.Data;

@Data
public class TaskResponse {

    private Long id;
    private String title;
    private Boolean completed;

    private Long userId;
    private String username;
}