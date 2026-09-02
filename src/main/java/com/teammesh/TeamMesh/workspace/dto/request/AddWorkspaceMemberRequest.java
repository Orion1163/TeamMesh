package com.teammesh.TeamMesh.workspace.dto.request;

import com.teammesh.TeamMesh.workspace.entity.WorkspaceRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddWorkspaceMemberRequest {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Role is required")
    private WorkspaceRole role;

    public String getEmail(){
        return email;
    }

    public WorkspaceRole getRole(){
        return role;
    }

}
