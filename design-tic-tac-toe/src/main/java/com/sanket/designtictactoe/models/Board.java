package com.sanket.designtictactoe.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Board {

    private final int dimension;
    private final List<List<Cell>> grid;

    public Board(int dimension) {
        this.dimension = dimension;
        this.grid = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                grid.get(i).add(new Cell(i, j));
            }
        }
    }

    public Cell getCell(int row, int column) {
        if (row >= 0 && row < dimension && column >= 0 && column < dimension) {
            return grid.get(row).get(column);
        }
        return null;
    }

    public void printBoard() {
        for (int i = 0; i < dimension; i++) {
            System.out.println();
            List<Cell> rows = grid.get(i);
            for (int j = 0; j < dimension; j++) {
                Cell cell = rows.get(j);
                Symbol symbol = cell.getSymbol();
                if (symbol == null) {
                    System.out.print("| |");
                } else {
                    System.out.print("|" + symbol.getSymbol() + "|");
                }
            }
        }
        System.out.println();
    }
}
