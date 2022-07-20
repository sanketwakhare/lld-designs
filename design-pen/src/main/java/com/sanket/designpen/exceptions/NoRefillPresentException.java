package com.sanket.designpen.exceptions;

public class NoRefillPresentException extends Exception {

    public NoRefillPresentException() {
        super("No refill present");
    }

    public NoRefillPresentException(String message) {
        super(message);
    }

}
