package com.teammesh.TeamMesh.workspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teammesh.TeamMesh.workspace.model.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
}
