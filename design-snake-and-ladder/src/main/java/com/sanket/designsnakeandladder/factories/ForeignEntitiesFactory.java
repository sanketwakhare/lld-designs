package com.sanket.designsnakeandladder.factories;

import com.sanket.designsnakeandladder.models.foreignentities.Ladder;
import com.sanket.designsnakeandladder.models.foreignentities.Snake;

public class ForeignEntitiesFactory {

    public static Snake createSnake(int startPosition, int endPosition) {
        return new Snake(startPosition, endPosition);
    }

    public static Ladder createLadder(int startPosition, int endPosition) {
        return new Ladder(startPosition, endPosition);
    }

}
