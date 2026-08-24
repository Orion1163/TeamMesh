package com.teammesh.TeamMesh.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teammesh.TeamMesh.auth.dto.request.RegisterRequest;
import com.teammesh.TeamMesh.user.User;
import com.teammesh.TeamMesh.user.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already registered");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getName(), request.getEmail(), hashedPassword);

        return userRepository.save(user);
    }
}
