package com.sanket.designsnakeandladder.strategies;

import com.sanket.designsnakeandladder.models.Board;
import com.sanket.designsnakeandladder.models.players.Button;
import com.sanket.designsnakeandladder.models.players.Player;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NormalMoveStrategy implements HandleMoveStrategy {

    @Override
    public boolean validateMove(Player player, int diceValue, Board board) {
        List<Button> buttons = player.getButtons();
        for (Button button : buttons) {
            if (button.getPosition() + diceValue <= board.getDimension()) return true;
        }
        return false;
    }

    @Override
    public void performMove(Player player, int diceValue, Board board) {
        // aks user which buttons to move
        List<Button> buttons = player.getButtons();
        for (Button button : buttons) {
            // TODO
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which button to move?");
    }
}
