package com.arunesh.irctc.irctc_backend.exceptions;



public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not Found !!");
    }

    public ResourceNotFoundException (String message)
    {
        super(message);
    }
}
