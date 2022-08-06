package com.sanket.designparkinglot.exceptions;

public class NoBillException extends Throwable {

    public NoBillException(long billId) {
        super("no bill with id " + billId);
    }
}
