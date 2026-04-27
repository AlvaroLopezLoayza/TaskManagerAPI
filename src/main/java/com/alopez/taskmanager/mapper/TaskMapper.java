package com.alopez.taskmanager.mapper;

import com.alopez.taskmanager.dto.request.TaskRequest;
import com.alopez.taskmanager.dto.response.TaskResponse;
import com.alopez.taskmanager.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    TaskResponse toResponse(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Task toEntity(TaskRequest request);
}