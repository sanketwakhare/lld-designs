package com.sanket.designsnakeandladder.models.foreignentities;

public class Snake extends ForeignEntity {

    public Snake(int startPosition, int endPosition) {
        super(ForeignEntityType.SNAKE, startPosition, endPosition);
    }
}
