package com.sanket.designsnakeandladder.strategies;

import com.sanket.designsnakeandladder.exceptions.InvalidButtonPosition;
import com.sanket.designsnakeandladder.models.Board;
import com.sanket.designsnakeandladder.models.players.Player;

public interface HandleMoveStrategy {

    boolean isValidMove(Player player, int diceValue, Board board);

    void performMove(Player player, int diceValue, Board board) throws Exception;
}
