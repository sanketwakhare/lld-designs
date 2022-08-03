package com.sanket.designparkinglot.exceptions;

public class NoSpotAvailableException extends Throwable {

    public NoSpotAvailableException() {
        super("no parking spot available");
    }
}
