package com.sanket.designparkinglot.exceptions;

public class NoSpotAvailableException extends Exception {

    public NoSpotAvailableException() {
        super("no parking spot available");
    }
}
