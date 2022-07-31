package com.sanket.designsnakeandladder.exceptions;

public class ButtonCannotMoveException extends Throwable {

    public ButtonCannotMoveException() {
        super("button cannot move ahead");
    }
}
