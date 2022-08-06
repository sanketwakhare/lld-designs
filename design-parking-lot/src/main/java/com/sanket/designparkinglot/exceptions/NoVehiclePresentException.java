package com.sanket.designparkinglot.exceptions;

public class NoVehiclePresentException extends Throwable {

    public NoVehiclePresentException(long vehicleId) {
        super("no vehicle with id " + vehicleId);
    }
}
