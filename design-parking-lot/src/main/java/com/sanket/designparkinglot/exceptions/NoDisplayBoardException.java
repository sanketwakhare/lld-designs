package com.sanket.designparkinglot.exceptions;

public class NoDisplayBoardException extends Throwable {
    public NoDisplayBoardException(Long displayBoardId) {
        super("no display board with id " + displayBoardId);
    }
}
