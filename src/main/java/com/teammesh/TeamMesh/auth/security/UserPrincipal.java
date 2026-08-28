package com.teammesh.TeamMesh.auth.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.teammesh.TeamMesh.user.entity.User;

public class UserPrincipal   {
    private final Long id;
    private final String email;
    private final String name;

    public UserPrincipal(User user){
        this.id=user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public String getName(){
        return name;
    }
    public Long getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }
}
