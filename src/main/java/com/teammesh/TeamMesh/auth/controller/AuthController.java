package com.teammesh.TeamMesh.auth.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teammesh.TeamMesh.auth.dto.request.LoginRequest;
import com.teammesh.TeamMesh.auth.dto.request.RegisterRequest;
import com.teammesh.TeamMesh.auth.dto.response.LoginResponse;
import com.teammesh.TeamMesh.auth.dto.response.UserResponse;
import com.teammesh.TeamMesh.auth.service.AuthService;
import com.teammesh.TeamMesh.user.User;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    private final AuthService authService;
    

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        User user = authService.register(request);
        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
