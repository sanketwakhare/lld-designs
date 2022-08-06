package com.sanket.designparkinglot.exceptions;

public class GateCreationException extends Throwable {

    public GateCreationException(String gateNumber) {
        super("gate can not be created " + gateNumber);
    }
}
