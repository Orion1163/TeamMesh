package com.teammesh.TeamMesh.workspace.dto.response;

public class WorkspaceResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long ownerId;

    public WorkspaceResponse(Long id, String name, String description, Long ownerId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public Long getOwnerId(){
        return ownerId;
    }
}
