package com.teammesh.TeamMesh.workspace.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teammesh.TeamMesh.workspace.entity.Workspace;


public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    List<Workspace> findByOwnerId(Long ownerId);   
}
