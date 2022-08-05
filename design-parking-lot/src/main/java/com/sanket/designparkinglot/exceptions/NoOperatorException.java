package com.sanket.designparkinglot.exceptions;

public class NoOperatorException extends Throwable {
    public NoOperatorException(Long operatorId) {
        super("no operator exist with id " + operatorId);
    }
}
