package com.teammesh.TeamMesh.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teammesh.TeamMesh.user.dto.response.CurrentUserResponse;
import com.teammesh.TeamMesh.user.entity.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(@AuthenticationPrincipal User user) {
        return new CurrentUserResponse(user.getId(), user.getName(), user.getEmail());
    }
    
}
