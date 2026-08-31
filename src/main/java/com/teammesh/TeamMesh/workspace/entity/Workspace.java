package com.teammesh.TeamMesh.workspace.entity;

import com.teammesh.TeamMesh.common.entity.BaseEntity;
import com.teammesh.TeamMesh.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workspaces")
public class Workspace extends BaseEntity {
    
    
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    protected Workspace(){

    }

    public Workspace(String name, String description, User owner){
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public User getOwner(){
        return owner;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
