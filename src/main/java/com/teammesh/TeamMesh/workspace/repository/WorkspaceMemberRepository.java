package com.teammesh.TeamMesh.workspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teammesh.TeamMesh.workspace.entity.WorkspaceMember;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {

    boolean existsByWorkspaceIdAndUserId(Long workspaceId, Long userId);

    List<WorkspaceMember> findByWorkspaceId(Long workspaceId);
} 
