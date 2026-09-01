package com.teammesh.TeamMesh.workspace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teammesh.TeamMesh.auth.security.UserPrincipal;
import com.teammesh.TeamMesh.workspace.dto.request.CreateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.request.UpdateWorkspaceRequest;
import com.teammesh.TeamMesh.workspace.dto.response.WorkspaceResponse;
import com.teammesh.TeamMesh.workspace.service.WorkspaceService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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
    
    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponse> getWorkspaceById(@PathVariable Long workspaceId, @AuthenticationPrincipal UserPrincipal principal){
            
        WorkspaceResponse response = workspaceService.getWorkspaceById(workspaceId, principal.getId());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponse> updateWorkspace(@PathVariable Long workspaceId,@Valid @RequestBody UpdateWorkspaceRequest request ,
        @AuthenticationPrincipal UserPrincipal principal){
        WorkspaceResponse response = workspaceService.updatWorkspace(workspaceId, principal.getId(), request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long workspaceId, @AuthenticationPrincipal UserPrincipal principal ){
            workspaceService.deleteWorkspace(workspaceId, principal.getId());

            return ResponseEntity.noContent().build();
    }
    
}