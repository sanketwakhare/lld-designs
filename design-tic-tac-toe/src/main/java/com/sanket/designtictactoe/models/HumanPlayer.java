package com.sanket.designtictactoe.models;

import java.util.Objects;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(Symbol symbol) {
        super(PlayerType.HUMAN, symbol);
    }

    @Override
    public Move makeMove(Board board) {
        Cell cell;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Want to Perform undo operation(Y/N)");
            String isUndo = scanner.nextLine();
            if (isUndo.equalsIgnoreCase("Y")) {
                return null;
            }
            System.out.print("Enter row number[1-based]: ");
            int row = scanner.nextInt();
            System.out.print("Enter column number[1-based]: ");
            int column = scanner.nextInt();

            cell = board.getCell(row - 1, column - 1);

        } while (!(!Objects.isNull(cell) && Objects.isNull(cell.getSymbol())));

        // set symbol within valid cell
        cell.setSymbol(this.getSymbol());

        // return move
        Move move = new Move();
        move.setPlayer(this);
        move.setCell(cell);
        return move;
    }
}
