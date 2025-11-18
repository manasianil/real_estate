package com.example.realestate.exception;

// This exception is used to indicate that a requested resource (like a property or user) was not found.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
