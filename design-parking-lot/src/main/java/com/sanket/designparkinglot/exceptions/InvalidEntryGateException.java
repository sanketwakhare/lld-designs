package com.sanket.designparkinglot.exceptions;

public class InvalidEntryGateException extends Throwable {

    public InvalidEntryGateException(long gateId) {
        super("invalid gate with id " + gateId);
    }

    public InvalidEntryGateException(String message, long gateId) {
        super(message + " " + gateId);
    }
}
