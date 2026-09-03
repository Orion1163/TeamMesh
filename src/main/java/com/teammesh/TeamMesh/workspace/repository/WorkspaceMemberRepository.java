package com.teammesh.TeamMesh.workspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teammesh.TeamMesh.workspace.entity.WorkspaceMember;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {

    boolean existsByWorkspaceIdAndUserId(Long workspaceId, Long userId);

    @Query("""
            SELECT wm
            FROM WorkspaceMember wm
            JOIN FETCH wm.user
            WHERE wm.workspace.id = :workspaceId
            """)
    List<WorkspaceMember> findMembersWithUserByWorkspaceId(@Param("workspaceId") Long workspaceId);

    Optional<WorkspaceMember> findByWorkspaceIdAndUserId(Long workspaceId, Long userId);
} 
