package com.teammesh.TeamMesh.workspace.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teammesh.TeamMesh.common.exception.ResourceNotFoundException;
import com.teammesh.TeamMesh.workspace.entity.WorkspaceMember;
import com.teammesh.TeamMesh.workspace.entity.WorkspaceRole;
import com.teammesh.TeamMesh.workspace.repository.WorkspaceMemberRepository;

@Service
public class WorkspaceAuthorizationService {
    
    private final WorkspaceMemberRepository workspaceMemberRepository;

    public WorkspaceAuthorizationService(WorkspaceMemberRepository workspaceMemberRepository){
        this.workspaceMemberRepository = workspaceMemberRepository;
    }

    @Transactional(readOnly = true)
    public WorkspaceMember getMembership(Long workspaceId, Long userId){

        return workspaceMemberRepository.findByWorkspaceIdAndUserId(workspaceId, userId).orElseThrow(() -> new ResourceNotFoundException("You are not a member of this workspace"));
    }

    @Transactional(readOnly = true)
    public WorkspaceRole getUserRole(Long workspaceId, Long userId){
        return getMembership(workspaceId, userId).getRole();
    }

    @Transactional(readOnly = true)
    public void requireOwner(Long workspaceId, Long userId){
        WorkspaceRole role = getUserRole(workspaceId, userId);

        if(role != WorkspaceRole.OWNER){
            throw new IllegalArgumentException("Only the workspace owner can perform this action");
        }
    }
}
