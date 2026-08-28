package com.teammesh.TeamMesh.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teammesh.TeamMesh.auth.security.UserPrincipal;
import com.teammesh.TeamMesh.user.dto.response.CurrentUserResponse;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new CurrentUserResponse(userPrincipal.getId(),userPrincipal.getName(), userPrincipal.getEmail());
    }
    
}
