package com.sanket.designpen.exceptions;

public class NoColorDefinedException extends Exception {

    public NoColorDefinedException() {
        super("no color defined");
    }

    public NoColorDefinedException(String message) {
        super(message);
    }
}
