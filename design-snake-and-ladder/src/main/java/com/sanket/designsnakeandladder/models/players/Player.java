package com.sanket.designsnakeandladder.models.players;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class Player {
    private Long id;
    private String name;
    private final PlayerType playerType;
    private final ColorType color;
    private final List<Button> buttons;

    public Player(PlayerType playerType, ColorType colorType, int buttonCount) {
        this.playerType = playerType;
        this.color = colorType;
        buttons = new ArrayList<>();
        while (buttonCount > 0) {
            buttons.add(new Button(colorType));
            buttonCount--;
        }
    }

    public List<Button> getButtons(ButtonStatus buttonStatus) {
        List<Button> list = new ArrayList<>();
        for (Button button : buttons) {
            if (button.getButtonStatus().equals(buttonStatus)) {
                list.add(button);
            }
        }
        return list;
    }

    @Deprecated
    public Map<Integer, List<Button>> getButtonPositions() {
        Map<Integer, List<Button>> buttonPositions = new HashMap<>();
        for (Button button : buttons) {
            int buttonPosition = button.getPosition();
            if (buttonPositions.containsKey(buttonPosition)) {
                List<Button> currButtonList = buttonPositions.get(buttonPosition);
                currButtonList.add(button);
            } else {
                List<Button> currButtonList = new ArrayList<>();
                currButtonList.add(button);
                buttonPositions.put(buttonPosition, currButtonList);
            }
        }
        return buttonPositions;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerType=" + playerType +
                ", color=" + color +
                ", buttons=" + buttons +
                '}';
    }
}
