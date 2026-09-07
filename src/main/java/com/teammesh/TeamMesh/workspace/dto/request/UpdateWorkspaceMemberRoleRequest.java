package com.teammesh.TeamMesh.workspace.dto.request;

import com.teammesh.TeamMesh.workspace.entity.WorkspaceRole;

import jakarta.validation.constraints.NotNull;

public class UpdateWorkspaceMemberRoleRequest {

    @NotNull(message = "Role is required")
    private WorkspaceRole role;

    public WorkspaceRole getRole(){
        return role;
    }

    public void setRole(WorkspaceRole role){
        this.role = role;
    }
}
