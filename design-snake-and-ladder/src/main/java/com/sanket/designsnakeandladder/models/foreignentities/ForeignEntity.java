package com.sanket.designsnakeandladder.models.foreignentities;

public abstract class ForeignEntity {
    private Long id;
    private final ForeignEntityType type;
    private final int startPosition;
    private final int endPosition;

    public ForeignEntity(ForeignEntityType foreignEntityType, int startPosition, int endPosition) {
        this.type = foreignEntityType;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public ForeignEntityType getType() {
        return type;
    }
}
