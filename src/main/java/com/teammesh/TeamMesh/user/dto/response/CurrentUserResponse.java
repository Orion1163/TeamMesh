package com.teammesh.TeamMesh.user.dto.response;

public class CurrentUserResponse {
    private final Long id;
    private final String name;
    private final String email;

    public CurrentUserResponse(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
}
