package com.sanket.designsnakeandladder.models.foreignentities;

public class Ladder extends ForeignEntity {

    public Ladder(int startPosition, int endPosition) {
        super(ForeignEntityType.LADDER, startPosition, endPosition);
    }
}
