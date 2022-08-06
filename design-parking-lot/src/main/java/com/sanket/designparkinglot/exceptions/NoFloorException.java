package com.sanket.designparkinglot.exceptions;

public class NoFloorException extends Throwable {

    public NoFloorException(long floorId) {
        super("no floor with id " + floorId);
    }
}
