package com.teammesh.TeamMesh.workspace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProjectRequest {

    @NotBlank(message = "Project name is required")
    @Size (max = 150, message = "Project Name cannot exceed 150 characters")
    private String name;

    @Size(max = 1000, message = "Project description cannot exceed 1000 characters")
    private String description;


    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}
