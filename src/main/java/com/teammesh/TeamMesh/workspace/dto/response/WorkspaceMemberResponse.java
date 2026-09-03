package com.teammesh.TeamMesh.workspace.dto.response;

import com.teammesh.TeamMesh.workspace.entity.WorkspaceRole;

public class WorkspaceMemberResponse {
    private Long userId;
    private String name;
    private String email;
    private WorkspaceRole role;

    public WorkspaceMemberResponse(Long userId, String name, String email, WorkspaceRole role){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getUserId(){
        return userId;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public WorkspaceRole getRole(){
        return role;
    }
}
