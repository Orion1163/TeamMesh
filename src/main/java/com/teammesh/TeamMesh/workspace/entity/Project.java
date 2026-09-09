package com.teammesh.TeamMesh.workspace.entity;

import com.teammesh.TeamMesh.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "projects")
public class Project extends BaseEntity {
    
    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional= false)
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    protected Project(){

    }

    public Project(String name, String description, Workspace workspace){
        this.name = name;
        this.description = description;
        this.workspace = workspace;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Workspace getWorkspace(){
        return workspace;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
