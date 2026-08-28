package com.teammesh.TeamMesh.workspace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teammesh.TeamMesh.user.entity.User;
import com.teammesh.TeamMesh.user.repository.UserRepository;
import com.teammesh.TeamMesh.workspace.dto.request.CreateWorkspaceRequest;
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

}
