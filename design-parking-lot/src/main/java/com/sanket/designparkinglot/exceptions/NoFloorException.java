package com.sanket.designparkinglot.exceptions;

public class NoFloorException extends Throwable {
    public NoFloorException(Long floorId) {
        super("no floor with id " + floorId);
    }
}
