package com.teammesh.TeamMesh.workspace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateWorkspaceRequest {
    
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Workspace name should be between 3 to 100 characters.")
    private String name;

    @Size(max = 100, message = "Description should not exceed 100 characters.")
    private String description;

    public UpdateWorkspaceRequest(){

    }
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
