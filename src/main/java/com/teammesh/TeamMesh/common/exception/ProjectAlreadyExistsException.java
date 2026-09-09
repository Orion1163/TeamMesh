package com.teammesh.TeamMesh.common.exception;

public class ProjectAlreadyExistsException extends RuntimeException {
    public ProjectAlreadyExistsException(String message){
        super(message);
    }
}
