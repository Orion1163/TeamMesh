package com.teammesh.TeamMesh.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teammesh.TeamMesh.auth.dto.request.LoginRequest;
import com.teammesh.TeamMesh.auth.dto.request.RegisterRequest;
import com.teammesh.TeamMesh.auth.dto.response.LoginResponse;
import com.teammesh.TeamMesh.common.exception.EmailAlreadyExistsException;
import com.teammesh.TeamMesh.user.entity.User;
import com.teammesh.TeamMesh.user.repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getName(), request.getEmail(), hashedPassword);

        return userRepository.save(user);
    }
    
    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid Email or Password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Email or Password");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return new LoginResponse(token);
    }
}
