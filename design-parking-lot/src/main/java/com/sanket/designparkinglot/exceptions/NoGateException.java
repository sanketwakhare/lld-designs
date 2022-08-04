package com.sanket.designparkinglot.exceptions;

public class NoGateException extends Throwable {

    public NoGateException() {
        super();
    }

    public NoGateException(Long id) {
        super("no gate with id " + id);
    }
}
