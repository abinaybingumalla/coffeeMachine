package com.dunzo.coffee.exception;

public class BeverageNotFoundException extends RuntimeException {
    public BeverageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeverageNotFoundException(String message) {
        super(message);
    }
}
