package com.teammesh.TeamMesh.workspace.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teammesh.TeamMesh.common.exception.ResourceNotFoundException;
import com.teammesh.TeamMesh.user.entity.User;
import com.teammesh.TeamMesh.user.repository.UserRepository;
import com.teammesh.TeamMesh.workspace.dto.request.CreateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.request.UpdateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.response.WorkspaceResponse;
import com.teammesh.TeamMesh.workspace.entity.Workspace;
import com.teammesh.TeamMesh.workspace.repository.WorkspaceRepository;

@Service
public class WorkspaceService {
    
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, UserRepository userRepository){
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
    }

    public WorkspaceResponse createWorkspace(CreateWorkspaceRequest request, Long userId){
        User owner = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

        Workspace workspace = new Workspace(request.getName(), request.getDescription(), owner);
 
        Workspace savedWorkspace = workspaceRepository.save(workspace);

        return new WorkspaceResponse(savedWorkspace.getId(), savedWorkspace.getName(), savedWorkspace.getDescription(), savedWorkspace.getOwner().getId());
    }

    public List<WorkspaceResponse> getMyWorkspaces(Long userId){
        List<Workspace> workspaces = workspaceRepository.findByOwnerId(userId);

        return workspaces.stream()
                .map(workspace -> new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getDescription(), workspace.getOwner().getId())).toList();
    }

    public WorkspaceResponse getWorkspaceById(Long workspaceId, Long userId){

        Workspace workspace = workspaceRepository.findByIdAndOwnerId(workspaceId, userId).orElseThrow(() -> new ResourceNotFoundException("Workspace not Found"));

        return new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getDescription(), workspace.getOwner().getId());
    }

    @Transactional
    public WorkspaceResponse updatWorkspace(Long workspaceId, Long userId, UpdateWorkspaceRequest request){
        Workspace workspace = workspaceRepository.findByIdAndOwnerId(workspaceId, userId).orElseThrow(() -> new ResourceNotFoundException("Workspace not Found"));

        workspace.setName(request.getName());
        workspace.setDescription(request.getDescription());

        return new WorkspaceResponse(workspace.getId(), workspace.getName(),workspace.getDescription(), workspace.getOwner().getId());
        
    }

}
