package com.teammesh.TeamMesh.workspace.dto.task.response;

import java.time.Instant;

import com.teammesh.TeamMesh.workspace.model.task.TaskPriority;
import com.teammesh.TeamMesh.workspace.model.task.TaskStatus;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long projectId;
    private Long assigneeId;
    private Instant dueDate;

    public TaskResponse(Long id, String title, String description, TaskStatus status, TaskPriority priority, Long projectId, Long assigneeId, Instant dueDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.assigneeId = assigneeId;
        this.dueDate = dueDate;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public TaskStatus getStatus(){
        return status;
    }

    public TaskPriority getPriority(){
        return priority;
    }

    public Long getProjectId(){
        return projectId;
    }

    public Long getAssigneeId(){
        return assigneeId;
    }

    public Instant getDueDate(){
        return dueDate;
    }
}
