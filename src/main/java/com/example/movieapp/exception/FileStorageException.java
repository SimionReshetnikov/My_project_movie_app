package com.example.movieapp.exception;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message, Throwable ex) {
        super(message, ex);
    }
}
