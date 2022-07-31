package com.sanket.designsnakeandladder.strategies;

import com.sanket.designsnakeandladder.exceptions.ButtonCannotMoveException;
import com.sanket.designsnakeandladder.exceptions.InvalidButtonPositionException;
import com.sanket.designsnakeandladder.models.Board;
import com.sanket.designsnakeandladder.models.foreignentities.ForeignEntity;
import com.sanket.designsnakeandladder.models.players.Button;
import com.sanket.designsnakeandladder.models.players.ButtonStatus;
import com.sanket.designsnakeandladder.models.players.Player;

import java.util.List;
import java.util.Scanner;

public class NormalMoveStrategy implements HandleMoveStrategy {

    @Override
    public boolean isValidMove(Player player, int diceValue, Board board) {
        List<Button> buttons = player.getButtons(ButtonStatus.IN_PROGRESS);
        for (Button button : buttons) {
            if (button.getPosition() + diceValue <= board.getDimension()) return true;
        }
        return false;
    }

    @Override
    public void performMove(Player player, int diceValue, Board board) throws Exception {
        // aks user which buttons to move
        List<Button> buttons = player.getButtons(ButtonStatus.IN_PROGRESS);
        System.out.print("Buttons which can be moved -> ");
        for (Button button : buttons) {
            System.out.print(button.getPosition() + " ");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which button to move? Type position: ");
        int buttonToMove = scanner.nextInt();
        Button validButton;
        try {
            validButton = isValidChoiceEntered(buttons, buttonToMove, diceValue, board);
        } catch (InvalidButtonPositionException | ButtonCannotMoveException e) {
            throw new Exception(e);
        }
        validButton.increasePositionBy(diceValue);
        if (validButton.getPosition() == board.getDimension()) {
            System.out.println("Congratulations! button " + validButton.getColor() + " finished");
            validButton.setButtonStatus(ButtonStatus.FINISHED);
        } else if (board.getForeignEntities().containsKey(validButton.getPosition())) {
            ForeignEntity foreignEntity = board.getForeignEntities().get(validButton.getPosition());
            validButton.setPosition(foreignEntity.getEndPosition());
        }
    }

    private Button isValidChoiceEntered(List<Button> buttons,
                                        int buttonToMove,
                                        int diceValue,
                                        Board board)
            throws InvalidButtonPositionException, ButtonCannotMoveException {
        for (Button button : buttons) {
            if (button.getPosition() == buttonToMove) {
                if (button.getPosition() + diceValue <= board.getDimension()) {
                    return button;
                }
                // throw custom exception, button cannot move further
                throw new ButtonCannotMoveException();
            }
        }
        // throw custom exception, invalid choice of button position
        throw new InvalidButtonPositionException();
    }
}
