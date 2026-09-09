package com.teammesh.TeamMesh.workspace.dto.response;

public class ProjectResponse {
    
    private Long id;
    private String name;
    private String description;
    private Long workspaceId;

    public ProjectResponse(Long id, String name, String description, Long workspaceId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.workspaceId = workspaceId;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Long getWorkspaceId(){
        return workspaceId;
    }
}
