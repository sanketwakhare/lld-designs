package com.sanket.designparkinglot.exceptions;

public class NoGateException extends Throwable {

    public NoGateException(long id) {
        super("no gate with id " + id);
    }
}
