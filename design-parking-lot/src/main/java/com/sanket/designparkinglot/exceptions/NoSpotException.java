package com.sanket.designparkinglot.exceptions;

public class NoSpotException extends Throwable {

    public NoSpotException(long spotId) {
        super("no spot with id " + spotId);
    }
}
