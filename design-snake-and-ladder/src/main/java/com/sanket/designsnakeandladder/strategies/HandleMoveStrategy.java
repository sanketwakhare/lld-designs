package com.sanket.designsnakeandladder.strategies;

import com.sanket.designsnakeandladder.models.Board;
import com.sanket.designsnakeandladder.models.players.Button;
import com.sanket.designsnakeandladder.models.players.Player;

public interface HandleMoveStrategy {

    boolean validateMove(Player player, int diceValue, Board board);

    void performMove(Player player, int diceValue, Board board);
}
