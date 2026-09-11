package com.teammesh.TeamMesh.workspace.dto.task.request;

import java.time.Instant;


import com.teammesh.TeamMesh.workspace.model.task.TaskPriority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTaskRequest {
    
    @NotBlank(message = "Task Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    private Long assigneeId;

    private Instant dueDate;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public TaskPriority getPriority(){
        return priority;
    }

    public Long getAssigneeId(){
        return assigneeId;
    }

    public Instant getDueDate(){
        return dueDate;
    }

}
