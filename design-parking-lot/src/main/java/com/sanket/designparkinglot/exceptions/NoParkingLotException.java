package com.sanket.designparkinglot.exceptions;

public class NoParkingLotException extends Throwable {

    public NoParkingLotException() {
        super("no parking lot exception");
    }

    public NoParkingLotException(Long id) {
        super("no parking lot exist with id " + id);
    }
}
