package com.sanket.designparkinglot.exceptions;

public class NoDisplayBoardException extends Throwable {

    public NoDisplayBoardException(long displayBoardId) {
        super("no display board with id " + displayBoardId);
    }
}
