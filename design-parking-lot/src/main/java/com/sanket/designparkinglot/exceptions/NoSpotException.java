package com.sanket.designparkinglot.exceptions;

public class NoSpotException extends Throwable {
    public NoSpotException(Long spotId) {
        super("no spot with id " + spotId);
    }
}
