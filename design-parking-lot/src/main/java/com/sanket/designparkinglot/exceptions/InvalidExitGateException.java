package com.sanket.designparkinglot.exceptions;

public class InvalidExitGateException extends Throwable {

    public InvalidExitGateException(long gateId) {
        super("invalid exit gate with id " + gateId);
    }
}
