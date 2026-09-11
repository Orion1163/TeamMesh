package com.teammesh.TeamMesh.workspace.model.task;

import java.time.Instant;

import org.hibernate.annotations.Fetch;

import com.teammesh.TeamMesh.common.entity.BaseEntity;
import com.teammesh.TeamMesh.user.entity.User;
import com.teammesh.TeamMesh.workspace.entity.Project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50) 
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Column(name = "due_date")
    private Instant dueDate;

    protected Task(){

    }

    public Task(String title, String description, TaskStatus status, TaskPriority priority, Project project, Instant dueDate){
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.project = project;
        this.dueDate = dueDate;
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

    public Project getProject(){
        return project;
    }

    public User getAssignee(){
        return assignee;
    }

    public Instant getDueDate(){
        return dueDate;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    public void setPriority(TaskPriority priority){
        this.priority = priority;
    }

    public void setAssignee(User assignee){
        this.assignee = assignee;
    }

    public void setDueDate(Instant dueDate){
        this.dueDate = dueDate;
    }
}
