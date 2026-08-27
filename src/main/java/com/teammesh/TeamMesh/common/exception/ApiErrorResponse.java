package com.teammesh.TeamMesh.common.exception;

import java.time.Instant;
import java.util.Map;

public class ApiErrorResponse {
    
    private boolean success;
    private int status;
    private String message;
    private Instant timestamp;
    private Map<String, String> errors;

    public ApiErrorResponse(boolean success, int status, String message, Instant timestamp, Map<String, String> errors){
        this.success = success;
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public boolean isSuccess(){
        return success;
    }

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public Instant getTimestamp(){
        return timestamp;
    }

    public Map<String, String> getErrors(){
        return errors;
    }
}
