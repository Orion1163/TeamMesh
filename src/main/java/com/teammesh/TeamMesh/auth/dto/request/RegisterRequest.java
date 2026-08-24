package com.teammesh.TeamMesh.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest{

    @NotBlank(message = "Name is Required")
    @Size(min = 2, max = 100,message = "Name Must be between 2 to 100 characters.")
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Email must be Valid")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 100, message = "Password must be between 8 to 100 characters.")
    private String password;


    public RegisterRequest(){

    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
}