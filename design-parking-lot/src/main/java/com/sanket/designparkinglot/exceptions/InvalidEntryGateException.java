package com.sanket.designparkinglot.exceptions;

public class InvalidEntryGateException extends Throwable {
    public InvalidEntryGateException(Long gateId) {
        super("invalid gate with id " + gateId);
    }

    public InvalidEntryGateException(String message, Long gateId) {
        super(message + " " + gateId);
    }
}
