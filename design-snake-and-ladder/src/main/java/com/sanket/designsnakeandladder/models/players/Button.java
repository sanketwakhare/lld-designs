package com.sanket.designsnakeandladder.models.players;

import lombok.Getter;

@Getter
public class Button {
    private Long id;
    private int position;
    private final ColorType color;

    private ButtonStatus buttonStatus;

    public Button(ColorType colorType) {
        super();
        this.color = colorType;
        this.position = -1;
        buttonStatus = ButtonStatus.LOCKED;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void increasePositionBy(int value) {
        this.position += value;
    }

    public void setButtonStatus(ButtonStatus buttonStatus) {
        this.buttonStatus = buttonStatus;
    }
}
