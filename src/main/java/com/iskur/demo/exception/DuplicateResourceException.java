package com.iskur.demo.exception;

public class DuplicateResourceException extends RuntimeException {

    // Constructor with message
    public DuplicateResourceException(String message) {
        super(message);
    }

    // Optional: Constructor with message and cause
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}