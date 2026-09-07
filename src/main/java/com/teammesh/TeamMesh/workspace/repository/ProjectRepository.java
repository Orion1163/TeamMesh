package com.teammesh.TeamMesh.workspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teammesh.TeamMesh.workspace.entity.Project;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByWorkspaceId(Long workspaceId);

    boolean existsByWorkspaceIdAndName(Long workspaceId, String name);
    
} 
