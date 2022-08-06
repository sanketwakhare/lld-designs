package com.sanket.designparkinglot.exceptions;

public class NoOperatorException extends Throwable {

    public NoOperatorException(long operatorId) {
        super("no operator exist with id " + operatorId);
    }
}
