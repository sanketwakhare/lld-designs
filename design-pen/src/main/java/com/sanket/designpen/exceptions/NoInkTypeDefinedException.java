package com.sanket.designpen.exceptions;

public class NoInkTypeDefinedException extends Exception {

    public NoInkTypeDefinedException() {
        super("no ink type defined");
    }

    public NoInkTypeDefinedException(String message) {
        super(message);
    }
}
