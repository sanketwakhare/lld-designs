package com.sanket.designsnakeandladder.models;

public class Dice {

    private final int minNumber;
    private final int maxNumber;

    public Dice(int minNumber, int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public int rollDice() {
        double number = Math.random() * (this.maxNumber - this.minNumber + 1) + this.minNumber;
        return (int) Math.floor(number);
    }
}
