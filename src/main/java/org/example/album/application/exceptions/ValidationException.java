package org.example.album.application.exceptions;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}