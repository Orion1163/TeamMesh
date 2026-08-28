package com.teammesh.TeamMesh.workspace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateWorkspaceRequest {
    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 100, message = "Workspace name must be between 3 and 100 characters")
    private String name;

    @Size(max = 100, message = "Description cannot exceed 100 characters")
    private String description;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
