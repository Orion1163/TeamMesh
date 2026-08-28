package com.teammesh.TeamMesh.workspace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teammesh.TeamMesh.auth.security.UserPrincipal;
import com.teammesh.TeamMesh.workspace.dto.request.CreateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.response.WorkspaceResponse;
import com.teammesh.TeamMesh.workspace.service.WorkspaceService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService){
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<WorkspaceResponse> createWorkspace(@Valid @RequestBody CreateWorkspaceRequest createWorkspaceRequest, @AuthenticationPrincipal UserPrincipal userPrincipal){
        WorkspaceResponse response = workspaceService.createWorkspace(createWorkspaceRequest, userPrincipal.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<WorkspaceResponse>> getMyWorkspaces(@AuthenticationPrincipal UserPrincipal principal){
        List<WorkspaceResponse> workspaces = workspaceService.getMyWorkspaces(principal.getId());

        return ResponseEntity.ok(workspaces);
    }
    
    
}