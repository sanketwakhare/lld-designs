package com.sanket.designsnakeandladder.models;

import com.sanket.designsnakeandladder.models.foreignentities.ForeignEntity;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Board {
    private final int dimension;
    private final Map<Integer, ForeignEntity> foreignEntities;

    public Board(int dimension, List<ForeignEntity> foreignEntities) {
        this.dimension = dimension;
        // create map of foreign entities
        this.foreignEntities = new HashMap<>();
        for (ForeignEntity foreignEntity : foreignEntities) {
            this.foreignEntities.put(foreignEntity.getStartPosition(), foreignEntity);
        }
    }

    public void display() {
        System.out.println("display Board");
    }
}
