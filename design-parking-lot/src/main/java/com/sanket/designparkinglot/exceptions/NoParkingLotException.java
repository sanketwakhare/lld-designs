package com.sanket.designparkinglot.exceptions;

public class NoParkingLotException extends Throwable {

    public NoParkingLotException(long id) {
        super("no parking lot exist with id " + id);
    }
}
